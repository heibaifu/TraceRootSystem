package com.traceroot.converter;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.utils.LocationUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 管道段到管道段DTO的转换
 */
public class PipelineSegment2PipeSegmentDTOConverter {

    public static PipeSegmentDTO convert(PipelineSegment pipelineSegment){

        PipeSegmentDTO pipeSegmentDTO = new PipeSegmentDTO() ;

        BeanUtils.copyProperties(pipelineSegment,pipeSegmentDTO);   //source,target

        pipeSegmentDTO.setStart(LocationUtil.string2DTOstring(pipelineSegment.getStart()));
        pipeSegmentDTO.setEnd(LocationUtil.string2DTOstring(pipelineSegment.getEnd()));

        return pipeSegmentDTO;
    }

    public static List<PipeSegmentDTO> convert(List<PipelineSegment> segmentList ){
        return segmentList.stream()
                .map(e ->convert(e))
                .collect(Collectors.toList());
    }

}
