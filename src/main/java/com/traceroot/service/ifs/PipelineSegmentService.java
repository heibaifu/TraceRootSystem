package com.traceroot.service.ifs;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.multikeysclass.PipelineSegmentMultiKeys;
import com.traceroot.dto.PipeSegmentDTO;

import java.util.List;

public interface PipelineSegmentService {

    PipelineSegment selectBySegmentId(String segmentId);

    List<PipelineSegment> selectByPipeId(String pipeId);

    List<PipeSegmentDTO> selectAll();

    List<PipeSegmentDTO> selectByWarning();

    PipelineSegment insert(PipelineSegment segment);

    PipelineSegment update(PipelineSegment segment);

    List<PipelineSegment> findByPipeIdAndSegmentSerialNumberAfter(String pipeId,Integer serialNumber);

    void deleteBySegmentId(String segmentId);

    void deleteByPipeId(String pipeId);
}
