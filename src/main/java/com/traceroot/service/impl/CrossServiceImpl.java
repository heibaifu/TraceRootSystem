package com.traceroot.service.impl;

import com.traceroot.dataobject.Boat;
import com.traceroot.dataobject.BoatTrace;
import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.service.ifs.CrossService;
import com.traceroot.utils.DoubleLocation;
import com.traceroot.utils.LocationUtil;
import com.traceroot.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CrossServiceImpl implements CrossService {

    @Autowired
    private PipelineSegmentServiceImpl segmentService;

    @Autowired
    private BoatTraceServiceImpl traceService;

    /**
     * 查找指定时间内在指定管道附近游弋的船只，及对应船只的轨迹
     * todo 根据给定范围进行查找的功能未开发
     * @param segmentId
     * @param startTime
     * @param endTime
     * @param accuracyDegree 模糊匹配精度
     * @return key是boatId，value是boatTrace
     */
    @Override
    public Map<String,List<BoatTrace>> findBoatNearSegmentDuringTime(String segmentId, Date startTime, Date endTime , Integer accuracyDegree) {

        //获取起始点经纬度对象
        PipelineSegment segment = segmentService.selectBySegmentId(segmentId);
        DoubleLocation startLocation = LocationUtil.string2doubleLocation(segment.getStart());
        DoubleLocation endLocation = LocationUtil.string2doubleLocation(segment.getEnd());

        //计算中心点的位置
        Double meanLng = 0.5 * (startLocation.getLongitude() + endLocation.getLongitude());
        Double meanLat = 0.5 * (startLocation.getLatitude() + endLocation.getLatitude());

        //构造模糊匹配表达式，设定小数点后位数为1位
        String fuzzyMatchingExpr = LocationUtil.buildFuzzyMatchingExpr(meanLng, meanLat, accuracyDegree);

        //搜索数据库
        List<BoatTrace> boatTraces = traceService.selectByRecordTimeBetweenAndRecordLocationIsLikeOrderByRecordTimeDesc(startTime, endTime, fuzzyMatchingExpr);

        //找出boatTraces里轨迹所对应的所有的boatId
        List<String> boatIdList = new ArrayList<>();
        String boatId = null;
        for (BoatTrace traceTemp : boatTraces){
            boatId = traceTemp.getBoatId();
            boatIdList.add(boatId);
        }

        //用HashSet剔除重复数据
        HashSet h = new HashSet(boatIdList);
        boatIdList.clear();
        boatIdList.addAll(h);

        Map result = new HashMap();
        //boatId相同的丢到一个list中
        List<BoatTrace> traceList;
        for (String id : boatIdList) {
            traceList = new ArrayList<>();
            for (BoatTrace trace : boatTraces) {
                if (trace.getBoatId().equals(id)) {
                    traceList.add(trace);
                }
            }
            traceList.sort((BoatTrace first, BoatTrace second) ->
                    first.traceSerialNumber2Int().compareTo(second.traceSerialNumber2Int()));
            result.put(id,traceList);
            traceList = null;
        }

        return result;
    }

    /**
     * 给定时间区间，按是否穿越管道查询船只
     * @param segmentId
     * @return TreeMap<Integer,Boat> 以穿越次数为key的、根据key排序的有序映射
     */
    @Override
    public TreeMap<Integer,String> selectByPassingPipelineSegment(String segmentId,Date startTime,Date endTime,Integer accuracyDegree) {

        //1.查找这段管道的坐标
        PipelineSegment pipelineSegment = segmentService.selectBySegmentId(segmentId);
        DoubleLocation segmentStart,segmentEnd;
        segmentStart = LocationUtil.string2doubleLocation(pipelineSegment.getStart());
        segmentEnd = LocationUtil.string2doubleLocation(pipelineSegment.getEnd());

        //2.查找在这段时间内经过管道的船只
        Map<String, List<BoatTrace>> boatListMap = findBoatNearSegmentDuringTime(segmentId, startTime, endTime,accuracyDegree);
        List<String> boatIdList = new ArrayList<>();
        boatListMap.forEach((t,v)-> boatIdList.add(t));

        TreeMap<Integer,String> result = new TreeMap<>();

        //3.循环比对指定时间内穿过管道附近的船只的轨迹
        for(String boatId : boatIdList){
            List<BoatTrace> traceList = boatListMap.get(boatId);
            Integer count = 0;
            if (traceList.size() == 1){
                count = 1;
            }else {
                for (int i = 0; i < traceList.size() - 1; i++) {
                    //4.统计穿越管道线的次数
                    if (MathUtil.intersection(segmentStart
                            , segmentEnd, LocationUtil.string2doubleLocation(traceList.get(i).getRecordLocation())
                            , LocationUtil.string2doubleLocation(traceList.get(i + 1).getRecordLocation()))
                            || traceList.get(i).getTraceSerialNumber() + 1 == traceList.get(i + 1).getTraceSerialNumber()) {
                        log.info(traceList.get(i).getTraceId());
                        count++;
                    }
                }
                result.put(count, boatId);
            }
        }

        return result;
    }
}
