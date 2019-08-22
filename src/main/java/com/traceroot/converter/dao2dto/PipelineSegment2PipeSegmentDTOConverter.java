package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dto.PipeSegmentDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 管道段到管道段DTO的转换
 */
public class PipelineSegment2PipeSegmentDTOConverter {

    public static PipeSegmentDTO convert(PipelineSegment pipelineSegment){

        if (pipelineSegment == null){
            return null;
        }

        PipeSegmentDTO pipeSegmentDTO = new PipeSegmentDTO() ;
        BeanUtils.copyProperties(pipelineSegment,pipeSegmentDTO);   //source,target

        pipeSegmentDTO.setStart(pipelineSegment.getStart());
        pipeSegmentDTO.setEnd(pipelineSegment.getEnd());

        return pipeSegmentDTO;
    }

    public static List<PipeSegmentDTO> convert(List<PipelineSegment> segmentList ){
        return segmentList.stream()
                .map(e ->convert(e))
                .collect(Collectors.toList());
    }

}
