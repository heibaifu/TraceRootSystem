package com.traceroot.converter.form2dto;

import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.form.PipelineSensorForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
public class PipelineSensorForm2SensorDTOConverter {

    public static PipelineSensorDTO convert (PipelineSensorForm pipelineSensorForm){

        PipelineSensorDTO pipelineSensorDTO=new PipelineSensorDTO();
        BeanUtils.copyProperties(pipelineSensorForm,pipelineSensorDTO);

        pipelineSensorDTO.setLocation("("+pipelineSensorForm.getLocation()+")");

        return pipelineSensorDTO;
    }

}
