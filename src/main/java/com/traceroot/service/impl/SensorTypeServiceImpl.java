package com.traceroot.service.impl;

import com.traceroot.converter.dao2dto.SensorType2SensorTypeDTOConverter;
import com.traceroot.dataobject.SensorType;
import com.traceroot.dto.SensorTypeDTO;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.SensorTypeRepository;
import com.traceroot.service.ifs.SensorTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class SensorTypeServiceImpl implements SensorTypeService {

    @Autowired
    private SensorTypeRepository repository;

    @Override
    public SensorTypeDTO save(SensorTypeDTO sensorTypeDTO) {
        SensorType sensorType=new SensorType();
        BeanUtils.copyProperties(sensorTypeDTO,sensorType);
        return SensorType2SensorTypeDTOConverter.convert(repository.save(sensorType));
    }

    @Override
    public SensorTypeDTO selectByTypeId(String typeId) {
        return SensorType2SensorTypeDTOConverter.convert(repository.findByTypeId(typeId));
    }

    @Override
    public SensorTypeDTO selectByTypeName(String typeName) {
        return SensorType2SensorTypeDTOConverter.convert(repository.findByTypeName(typeName));
    }

    /*@Override
    public SensorType updateByTypeId(String typeId, String updateName) {

        SensorType sensorType=new SensorType();
        sensorType=repository.findByTypeId(typeId);
        sensorType.setTypeName(updateName);
        SensorType result=repository.save(sensorType);
        return result;
    }*/

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
