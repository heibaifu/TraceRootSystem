package com.traceroot.service.impl;

import com.traceroot.dataobject.SensorType;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.SensorTypeRepository;
import com.traceroot.service.SensorTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class SensorTypeServiceImpl implements SensorTypeService {

    @Autowired
    private SensorTypeRepository repository;

    @Override
    public SensorType save(SensorType sensorType) {
        return repository.save(sensorType);
    }

    @Override
    public SensorType selectByTypeId(String typeId) {

        return repository.findByTypeId(typeId);
    }

    @Override
    public SensorType selectByTypeName(String typeName) {
        return repository.findByTypeName(typeName);
    }

    @Override
    public SensorType updateByTypeId(String typeId, String updateName) {

        SensorType sensorType=new SensorType();
        sensorType=repository.findByTypeId(typeId);
        sensorType.setTypeName(updateName);
        SensorType result=repository.save(sensorType);
        return result;
    }

    @Override
    public void deleteByTypeId(String typeId) {
        SensorType sensorType = repository.findByTypeId(typeId);
        if (sensorType==null){
            throw new PipeException(ResultEnum.SENSOR_TYPE_NOT_EXIST);
        }
        repository.delete(sensorType);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
