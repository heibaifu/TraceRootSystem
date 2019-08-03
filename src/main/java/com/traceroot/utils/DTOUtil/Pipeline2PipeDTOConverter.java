package com.traceroot.utils.DTOUtil;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.dto.PipeDTO;
import com.traceroot.utils.LocationUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Pipeline2PipeDTOConverter {

    public static PipeDTO convert(Pipeline pipeline){

        PipeDTO pipeDTO = new PipeDTO() ;

        pipeDTO.setPipeId(pipeline.getPipeId());
        pipeDTO.setSource(LocationUtil.string2DoubleArray(pipeline.getSource()));
        pipeDTO.setDestination(LocationUtil.string2DoubleArray(pipeline.getDestination()));

        return pipeDTO;
    }

    public static List<PipeDTO> convert(List<Pipeline> pipelineList ){
        return pipelineList.stream()
                .map(e ->convert(e))
                .collect(Collectors.toList());
    }
}
