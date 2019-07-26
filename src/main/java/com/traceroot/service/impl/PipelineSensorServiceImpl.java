package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dataobject.SensorStatus;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineSensorRepository;
import com.traceroot.repository.SensorStatusRepository;
import com.traceroot.service.ifs.PipelineSensorService;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PipelineSensorServiceImpl implements PipelineSensorService {

    @Autowired
    private PipelineSensorRepository repository;

    @Autowired
    private SensorStatusRepository statusRepository;

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

        PipelineSensor result=repository.save(sensor);

        //增加一条传感器状态记录
        SensorStatus sensorStatus = new SensorStatus(RandomUtil.genUniqueId(),result.getSensorId(),result.getPresentStatus());
        statusRepository.save(sensorStatus);

        return result;
    }

    @Override
    public PipelineSensor updateByStatus(String sensorId, String updateStatus) {
        PipelineSensor pipelineSensor=repository.findBySensorId(sensorId);
        //判断是否存在
        if (pipelineSensor==null){
            throw new PipeException(ResultEnum.SENSOR_NOT_EXIST);
        }
        pipelineSensor.setPresentStatus(updateStatus);
        PipelineSensor result=repository.save(pipelineSensor);

        //增加一条传感器状态记录
        SensorStatus sensorStatus = new SensorStatus(RandomUtil.genUniqueId(),sensorId,updateStatus);
        statusRepository.save(sensorStatus);
        return result;
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
