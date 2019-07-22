package com.traceroot.service;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.multikeysclass.PipelineSegmentMultiKeys;

import java.util.List;

public interface PipelineSegmentService {

    PipelineSegment selectBySegmentId(String segmentId);

    List<PipelineSegment> selectByPipeId(String pipeId);

    PipelineSegment save(PipelineSegment segment);

    void deleteById(PipelineSegmentMultiKeys keys);
}
