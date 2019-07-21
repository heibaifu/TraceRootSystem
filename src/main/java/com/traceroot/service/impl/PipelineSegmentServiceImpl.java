package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.repository.PipelineSegmentRepository;
import com.traceroot.service.PipelineSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PipelineSegmentServiceImpl implements PipelineSegmentService {

    @Autowired
    PipelineSegmentRepository repository;

    @Override
    public PipelineSegment selectBySegmentId(String segmentId) {
        return repository.findBySegmentId(segmentId);
    }

    @Override
    public List<PipelineSegment> selectBypipeId(String pipeId) {
        return repository.findByPipeIdOrderBySegmentSerialNumber(pipeId);
    }

    @Override
    public PipelineSegment save(PipelineSegment segment) {
        return repository.save(segment);
    }

    @Override
    public void delete(PipelineSegment segment){
        repository.delete(segment);
    }
}
