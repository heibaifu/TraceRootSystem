package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.dto.PipeDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Pipeline2PipeDTOConverter {

    public static PipeDTO convert(Pipeline pipeline){
        if (pipeline == null){
            return null;
        }
        PipeDTO pipeDTO = new PipeDTO() ;

        pipeDTO.setPipeId(pipeline.getPipeId());
        pipeDTO.setSource(pipeline.getSource());
        pipeDTO.setDestination(pipeline.getDestination());
        /*pipeDTO.setCreateTime(pipeline.getCreateTime());*/

        return pipeDTO;
    }

    public static List<PipeDTO> convert(List<Pipeline> pipelineList ){
        return pipelineList.stream()
                .map(e ->convert(e))
                .collect(Collectors.toList());
    }
}
