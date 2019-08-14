package com.traceroot.converter.form2dto;

import com.traceroot.dto.PipeDTO;
import com.traceroot.form.PipeForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PipeForm2PipeDTOConverter {

    public static PipeDTO convert(PipeForm pipeForm )/* throws ParseException*/{
        PipeDTO pipeDTO = new PipeDTO();

        pipeDTO.setPipeId(pipeForm.getPipeId());
        pipeDTO.setSource(pipeForm.getSource());
        pipeDTO.setDestination(pipeForm.getDestination());

        /*if (pipeForm.getCreateTime() != null) {
            pipeDTO.setCreateTime(TimeUtil.string2Timestamp(pipeForm.getCreateTime()));
        }*/

        return pipeDTO;
    }
}
