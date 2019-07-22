package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dataobject.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineRepository;
import com.traceroot.repository.PipelineSensorRepository;
import com.traceroot.service.PipelineSensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PipelineSensorServiceImpl implements PipelineSensorService {

    @Autowired
    private PipelineSensorRepository repository;

    @Override
    public List<PipelineSensor> selectBySegmentId(String segmentId) {
        return repository.findBySegmentId(segmentId);
    }

    @Override
    public PipelineSensor selectBySensorId(String sensorId) {
        return repository.findBySensorId(sensorId);
    }

    @Override
    public List<PipelineSensor> selectByPipeId(String pipeId) {
        return repository.findByPipeId(pipeId);
    }

    @Override
    public PipelineSensor save(PipelineSensor sensor) {
        return repository.save(sensor);
    }

    @Override
    public void deleteBySensorId(String SensorId) {
        PipelineSensor sensor = repository.findBySensorId(SensorId);
        if (sensor==null){
            throw new PipeException(ResultEnum.SENSOR_NOT_EXIST);
        }
        repository.delete(sensor);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
