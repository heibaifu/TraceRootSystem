package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.utils.LocationUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PipelineSensor2SensorDTOConverter {

    public static PipelineSensorDTO convert(PipelineSensor pipelineSensor){
        if (pipelineSensor == null){
            return null;
        }
        PipelineSensorDTO pipelineSensorDTO =new PipelineSensorDTO();
        BeanUtils.copyProperties(pipelineSensor, pipelineSensorDTO);
        pipelineSensorDTO.setLocation(pipelineSensor.getLocation());
        return pipelineSensorDTO;
    }

    /*list的转换*/
    public static List<PipelineSensorDTO> convert(List<PipelineSensor> pipelineSensorList ) {
        return pipelineSensorList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
