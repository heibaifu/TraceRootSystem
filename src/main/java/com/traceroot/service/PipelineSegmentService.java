package com.traceroot.service;

import com.traceroot.dataobject.PipelineSegment;

import java.util.List;

public interface PipelineSegmentService {

    PipelineSegment selectBySegmentId(String segmentId);

    List<PipelineSegment> selectBypipeId(String pipeId);

    PipelineSegment save(PipelineSegment segment);

    void delete(PipelineSegment segment);
}
