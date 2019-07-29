package com.traceroot.repository;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.multikeysclass.PipelineSegmentMultiKeys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PipelineSegmentRepository extends JpaRepository<PipelineSegment, PipelineSegment> {

    PipelineSegment findBySegmentId(String segmentId);

    List<PipelineSegment> findByPipeId(String pipeId);

    List<PipelineSegment> findByPipeIdOrderBySegmentSerialNumber(String pipeId);

    List<PipelineSegment> findByPipeIdAndSegmentSerialNumberAfterOrderBySegmentSerialNumber(String pipeId,Integer serialNumber);

    Integer countPipelineSegmentByPipeId(String pipeId);

}
