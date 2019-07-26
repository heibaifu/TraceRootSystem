package com.traceroot.service.impl;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineRepository;
import com.traceroot.service.ifs.PipelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PipelineServiceImpl implements PipelineService {

    @Autowired
    private PipelineRepository repository;

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

    @Override
    public void deleteById(String pipeId) {
        Pipeline pipeline = repository.findByPipeId(pipeId);
        if (pipeline==null){
            throw new PipeException(ResultEnum.PIPE_NOT_EXIST);
        }
        repository.delete(pipeline);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
