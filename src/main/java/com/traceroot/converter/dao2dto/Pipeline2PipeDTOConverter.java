package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.dto.PipeDTO;
import com.traceroot.utils.LocationUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Pipeline2PipeDTOConverter {

    public static PipeDTO convert(Pipeline pipeline){

        PipeDTO pipeDTO = new PipeDTO() ;

        pipeDTO.setPipeId(pipeline.getPipeId());
        pipeDTO.setSource(LocationUtil.string2DTOstring(pipeline.getSource()));
        pipeDTO.setDestination(LocationUtil.string2DTOstring(pipeline.getDestination()));
        /*pipeDTO.setCreateTime(pipeline.getCreateTime());*/

        return pipeDTO;
    }

    public static List<PipeDTO> convert(List<Pipeline> pipelineList ){
        return pipelineList.stream()
                .map(e ->convert(e))
                .collect(Collectors.toList());
    }
}
