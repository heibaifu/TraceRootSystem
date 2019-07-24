package com.traceroot.service;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.multikeysclass.PipelineSegmentMultiKeys;

import java.util.List;

public interface PipelineSegmentService {

    PipelineSegment selectBySegmentId(String segmentId);

    List<PipelineSegment> selectByPipeId(String pipeId);

    PipelineSegment insert(PipelineSegment segment);

    PipelineSegment update(PipelineSegment segment);

    void deleteById(String segmentId);

    void deleteBy(String pipeId);
}
