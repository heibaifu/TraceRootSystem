package com.traceroot.service.impl;

import com.traceroot.dataobject.BoatTrace;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.BoatException;
import com.traceroot.repository.BoatTraceRepository;
import com.traceroot.service.ifs.BoatTraceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BoatTraceServiceImpl implements BoatTraceService {

    @Autowired
    private BoatTraceRepository boatTraceRepository;

    @Override
    public BoatTrace selectByTraceId(String traceId) {
        return boatTraceRepository.findByTraceId(traceId);
    }

    @Override
    public List<BoatTrace> selectByBoatId(String boatId) {
        return boatTraceRepository.findByBoatIdOrderByTraceSerialNumber(boatId);
    }

    @Override
    public List<BoatTrace> selectByRecordTimeBetween(Date startTime, Date endTime) {
        return boatTraceRepository.findByRecordTimeBetweenOrderByRecordTimeDesc(startTime,endTime);
    }

    @Override
    public List<BoatTrace> selectByBoatIdAndRecordTimeBetween(String traceId, Date startTime, Date endTime) {
        return boatTraceRepository.findByBoatIdAndRecordTimeBetweenOrderByRecordTimeDesc(traceId,startTime,endTime);
    }

    /**
     * 根据（指定位置附近）模糊匹配表达式，返回指定时间段内的轨迹数据
     * 例子："(+116.3%,+39.9%)"
     * @param startTime
     * @param endTime
     * @param location
     * @return
     */
    @Override
    public List<BoatTrace> selectByRecordTimeBetweenAndRecordLocationIsLikeOrderByRecordTimeDesc(Date startTime, Date endTime, String location) {
        return boatTraceRepository.findByRecordTimeBetweenAndRecordLocationIsLikeOrderByRecordTimeDesc(startTime,endTime,location);
    }

    /**
     * 根据（指定位置附近）模糊匹配表达式，返回轨迹数据
     * 例子："(+116.3%,+39.9%)"
     * @param location
     * @return
     */
    @Override
    public List<BoatTrace> selectByLocationIsLikeOrderByRecordTimeDesc(String location) {
        return boatTraceRepository.findByRecordLocationIsLikeOrderByRecordTimeDesc(location);
    }

    @Override
    public BoatTrace insert(BoatTrace boatTrace) {
        Integer number = boatTraceRepository.countBoatTraceByBoatId(boatTrace.getBoatId())+1;
        boatTrace.setTraceSerialNumber(number.toString());
        return boatTraceRepository.save(boatTrace);
    }

    @Override
    public void deleteByBoatId(String boatId) {
        List<BoatTrace> boatTraceList = boatTraceRepository.findByBoatId(boatId);
        if (boatTraceList.size()==0){
            throw new BoatException(ResultEnum.BOAT_NOT_EXIST);
        }
        boatTraceRepository.deleteAll(boatTraceList);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }

    @Override
    public void deleteByTraceId(String traceId) {
        BoatTrace byTraceId = boatTraceRepository.findByTraceId(traceId);
        if (byTraceId==null){
            throw new BoatException(ResultEnum.TRACE_NOT_EXIST);
        }
        boatTraceRepository.delete(byTraceId);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
