package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineSegmentRepository;
import com.traceroot.service.PipelineSegmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PipelineSegmentServiceImpl implements PipelineSegmentService {

    @Autowired
    private PipelineSegmentRepository repository;

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

    @Override
    public void deleteBy(String pipeId) {
        List<PipelineSegment> segmentList = repository.findByPipeId(pipeId);
        if (segmentList.size()==0){
            throw new PipeException(ResultEnum.PIPE_NOT_EXIST);
        }
        repository.deleteAll(segmentList);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
