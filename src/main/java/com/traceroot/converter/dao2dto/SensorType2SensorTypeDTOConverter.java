package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.SensorType;
import com.traceroot.dto.SensorTypeDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SensorType2SensorTypeDTOConverter {

    public static SensorTypeDTO convert(SensorType type){
        if (type == null){
            return null;
        }
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO() ;

        BeanUtils.copyProperties(type,sensorTypeDTO);

        return sensorTypeDTO;
    }

    public static List<SensorTypeDTO> convert(List<SensorType> types ) {
        return types.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
