package com.traceroot.controller;

import com.traceroot.dto.BoatTraceDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.BoatException;
import com.traceroot.service.ifs.CrossService;
import com.traceroot.service.impl.BoatTraceServiceImpl;
import com.traceroot.utils.ResultVOUtil;
import com.traceroot.utils.TimeUtil;
import com.traceroot.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/boat")
@Slf4j
public class BoatManageController {

    @Autowired
    BoatTraceServiceImpl traceService;

    @Autowired
    CrossService crossService;

    /**
     * 按照船只id查找trace
     * @param boatId
     * @return
     */
    @ResponseBody
    @GetMapping("/findbyboatid")
    public ResultVO<List<BoatTraceDTO>> findBoatTraceByBoatId(@RequestParam(value = "boatid",required = true)String boatId){
        List<BoatTraceDTO> traceDTOList = traceService.selectByBoatId(boatId);
        if (traceDTOList.size()==0){
            log.error("【查找船只轨迹】船只轨迹不存在，boatid={}",boatId);
            throw new BoatException(ResultEnum.BOAT_TRACE_NOT_EXIST);
        }
        return ResultVOUtil.success(traceDTOList);
    }

    /**
     * 按照时间区间查找trace
     * @param startTime
     * @param endTime
     * @return
     */
    @ResponseBody
    @GetMapping("/findbytimesection")
    public ResultVO<List<BoatTraceDTO>> findBoatTraceByTimeSection(@RequestParam(value = "starttime",required = true)String startTime,
                                                                   @RequestParam(value = "endtime",required = true)String endTime){
        List<BoatTraceDTO> traceDTOList = null;
        try {
            traceDTOList = traceService.selectByRecordTimeBetween(TimeUtil.string2Timestamp(startTime), TimeUtil.string2Timestamp(endTime));
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
            return ResultVOUtil.error(ResultEnum.TIME_FORMAT_ERROR.getCode(),ResultEnum.TIME_FORMAT_ERROR.getMessage());
        }
        if (traceDTOList.size()==0){
            log.error("【查找船只轨迹】该时间区间内船只轨迹不存在，starttime={},endtime={}",startTime,endTime);
            return ResultVOUtil.error(ResultEnum.BOAT_TRACE_NOT_EXIST.getCode(),ResultEnum.BOAT_TRACE_NOT_EXIST.getMessage());
        }
        return ResultVOUtil.success(traceDTOList);
    }

    /**
     * 查找在指定时间指定经纬度范围内，穿越指定管道段的船只及其穿越次数
     * @param segmentId
     * @param startTime
     * @param endTime
     * @param accuracyDegree
     * @return
     */
    @ResponseBody
    @GetMapping("/findwarningBoat")
    public ResultVO<List<BoatTraceDTO>> findWarningBoatNearSegmentDuringTime(@RequestParam(value = "segmentid",required = true)String segmentId,
                                                                             @RequestParam(value = "starttime",required = false)String startTime,
                                                                             @RequestParam(value = "endtime",required = false)String endTime,
                                                                             @RequestParam(value = "accuracydegree",required = false,defaultValue = "1")Integer accuracyDegree){
        if (endTime == null || endTime.isEmpty()){
            endTime = TimeUtil.presentTime();
        }
        if (startTime == null || startTime.isEmpty()){
            startTime = TimeUtil.getPastDate(3);
        }
        NavigableMap<Integer, List<String>> map = null;
        try {
            map = crossService.selectByPassingPipelineSegment(segmentId, TimeUtil.string2Timestamp(startTime), TimeUtil.string2Timestamp(endTime), accuracyDegree);
        } catch (ParseException e) {
            log.error("【时间设定异常或格式错误】,startTime={},endTime={}",startTime,endTime);
            return ResultVOUtil.error(ResultEnum.TIME_FORMAT_ERROR.getCode(),ResultEnum.TIME_FORMAT_ERROR.getMessage());
        }
        if (map.size()==0){
            log.error("【查找船只轨迹】该时间、精度区间内船只不存在，starttime={},endtime={},accuracydegree={}",startTime,endTime,accuracyDegree);
            return ResultVOUtil.error(ResultEnum.NO_SURROUND_BOAT_FOUND.getCode(),ResultEnum.NO_SURROUND_BOAT_FOUND.getMessage());
        }
        return ResultVOUtil.success(map);
    }

}
