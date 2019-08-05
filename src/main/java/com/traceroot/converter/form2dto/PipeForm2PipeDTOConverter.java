package com.traceroot.converter.form2dto;

import com.traceroot.dto.PipeDTO;
import com.traceroot.form.PipeForm;
import com.traceroot.utils.String2TimestampUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PipeForm2PipeDTOConverter {

    public static PipeDTO convert(PipeForm pipeForm )/* throws ParseException*/{
        PipeDTO pipeDTO = new PipeDTO();

        pipeDTO.setPipeId(pipeForm.getPipeId());
        pipeDTO.setSource("(" + pipeForm.getSource() + ")");
        pipeDTO.setDestination("(" + pipeForm.getDestination() + ")");

        /*if (pipeForm.getCreateTime() != null) {
            pipeDTO.setCreateTime(String2TimestampUtil.string2Time(pipeForm.getCreateTime()));
        }*/

        return pipeDTO;
    }
}
