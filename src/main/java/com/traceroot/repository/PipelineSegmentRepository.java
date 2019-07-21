package com.traceroot.repository;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.multikeysclass.PipelineSegmentMultiKeys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PipelineSegmentRepository extends JpaRepository<PipelineSegment, PipelineSegmentMultiKeys> {

    PipelineSegment findBySegmentId(String segmentId);

    List<PipelineSegment> findByPipeIdOrderBySegmentSerialNumber(String pipeId);

}
