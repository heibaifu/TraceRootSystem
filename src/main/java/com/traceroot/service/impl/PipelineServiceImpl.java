package com.traceroot.service.impl;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.repository.PipelineRepository;
import com.traceroot.service.PipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PipelineServiceImpl implements PipelineService {

    @Autowired
    PipelineRepository repository;

    @Override
    public Pipeline selectByPipeId(String pipeId) {
        return  repository.findByPipeId(pipeId);
    }

    @Override
    public List<Pipeline> selectAll() {
        return repository.findAll();
    }

    @Override
    public Pipeline save(Pipeline pipeline) {
        return repository.save(pipeline);
    }
}
