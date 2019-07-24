package com.traceroot.service.impl;

import com.traceroot.dataobject.BoatTrace;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.BoatException;
import com.traceroot.repository.BoatTraceRepository;
import com.traceroot.service.BoatTraceService;
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
