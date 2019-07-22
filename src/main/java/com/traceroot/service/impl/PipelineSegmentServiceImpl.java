package com.traceroot.service.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.exception.PipeException;
import com.traceroot.dataobject.multikeysclass.PipelineSegmentMultiKeys;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineSegmentRepository;
import com.traceroot.service.PipelineSegmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class PipelineSegmentServiceImpl implements PipelineSegmentService {

    @Autowired
    PipelineSegmentRepository repository;

    @Override
    public PipelineSegment selectBySegmentId(String segmentId) {
        return repository.findBySegmentId(segmentId);
    }

    @Override
    public List<PipelineSegment> selectByPipeId(String pipeId) {
        return repository.findByPipeIdOrderBySegmentSerialNumber(pipeId);
    }

    @Override
    public PipelineSegment insert(PipelineSegment segment) {
        Integer amount = repository.countPipelineSegmentByPipeId(segment.getPipeId());
        segment.setSegmentSerialNumber(amount+1);
        return repository.save(segment);
    }

    @Override
    public PipelineSegment update(PipelineSegment segment) {
        PipelineSegment pipelineSegment = repository.findBySegmentId(segment.getSegmentId());
        if (pipelineSegment==null){
            throw new PipeException(ResultEnum.PIPE_SEGMENT_NOT_EXIST);
        }
        return repository.save(segment);
    }

    //删除管道段，先查询有没有再删除
    @Override
    public void deleteById(String segmentId){
        PipelineSegment pipelineSegment = repository.findBySegmentId(segmentId);
        if (pipelineSegment==null){
            throw new PipeException(ResultEnum.PIPE_SEGMENT_NOT_EXIST);
        }
        repository.delete(pipelineSegment);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
