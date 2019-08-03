package com.traceroot.utils.DTOUtil;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.utils.LocationUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PipelineSegment2PipeSegmentDTOConverter {

    public static PipeSegmentDTO convert(PipelineSegment pipelineSegment){

        PipeSegmentDTO pipeSegmentDTO = new PipeSegmentDTO() ;

        BeanUtils.copyProperties(pipelineSegment,pipeSegmentDTO);

        pipeSegmentDTO.setStart(LocationUtil.string2DoubleArray(pipelineSegment.getStart()));
        pipeSegmentDTO.setEnd(LocationUtil.string2DoubleArray(pipelineSegment.getEnd()));

        return pipeSegmentDTO;
    }

    public static List<PipeSegmentDTO> convert(List<PipelineSegment> segmentList ){
        return segmentList.stream()
                .map(e ->convert(e))
                .collect(Collectors.toList());
    }

}
