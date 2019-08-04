package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineSegmentRepository;
import com.traceroot.service.ifs.PipelineSegmentService;
import com.traceroot.utils.DTOUtil.PipelineSegment2PipeSegmentDTOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class PipelineSegmentServiceImpl implements PipelineSegmentService {

    @Autowired
    private PipelineSegmentRepository repository;

    @Autowired
    private PipelineSensorServiceImpl pipelineSensorService;

    //将查出来的列表转换为DTO列表
    @Override
    public List<PipeSegmentDTO> selectAll() {
        List<PipelineSegment> segmentList = repository.findAll();
        List<PipeSegmentDTO> segmentDTOList = PipelineSegment2PipeSegmentDTOConverter.convert(segmentList);
        return segmentDTOList;
    }

    @Override
    public PipelineSegment selectBySegmentId(String segmentId) {
        return repository.findBySegmentId(segmentId);
    }

    @Override
    public List<PipelineSegment> selectByPipeId(String pipeId) {
        return repository.findByPipeIdOrderBySegmentSerialNumber(pipeId);
    }

    /**
     * 返回有问题的管道段
     * @return
     */
    @Override
    public List<PipeSegmentDTO> selectByWarning() {

        //todo 传感器状态需注意，不止查找一种状态
        List<PipelineSensor> pipelineSensors=pipelineSensorService.selectByPresentStatus(SensorStatusEnum.ABNORMAL.getCode());
        List<PipelineSegment> segmentList =new ArrayList<>();
        for (int i=0;i<pipelineSensors.size();i++){
            //从传感器列表的第0项开始查找
            String segmentId=pipelineSensors.get(i).getSegmentId();
            segmentList.add(repository.findBySegmentId(segmentId));
        }

        //用HashSet剔除重复数据
        HashSet h = new HashSet(segmentList);
        segmentList.clear();
        segmentList.addAll(h);

        List<PipeSegmentDTO> segmentDTOList = PipelineSegment2PipeSegmentDTOConverter.convert(segmentList);
        return segmentDTOList;
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

    //查找在serialNumber之后的部分
    @Override
    public List<PipelineSegment> findByPipeIdAndSegmentSerialNumberAfter(String pipeId, Integer serialNumber) {

        return repository.findByPipeIdAndSegmentSerialNumberAfterOrderBySegmentSerialNumber(pipeId, serialNumber);
    }

    //删除管道段，先查询有没有再删除，删除时同时修改其他段的serial_number
    @Override
    public void deleteBySegmentId(String segmentId){
        PipelineSegment pipelineSegment = repository.findBySegmentId(segmentId);
        if (pipelineSegment==null){
            throw new PipeException(ResultEnum.PIPE_SEGMENT_NOT_EXIST);
        }

        //修改其余管道段的序列号
        Integer number=pipelineSegment.getSegmentSerialNumber();
        String pipeId=pipelineSegment.getPipeId();
        int size=repository.countPipelineSegmentByPipeId(pipeId);
        List<PipelineSegment> pipelineSegments=repository.findByPipeIdAndSegmentSerialNumberAfterOrderBySegmentSerialNumber(pipeId,number);

        if (number != size){
            for (int i=0;i<pipelineSegments.size();i++){
                pipelineSegments.get(i).setSegmentSerialNumber(i+number);
                repository.save(pipelineSegments.get(i));
            }
        }
        repository.delete(pipelineSegment);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }

    @Override
    public void deleteByPipeId(String pipeId) {
        List<PipelineSegment> segmentList = repository.findByPipeId(pipeId);
        if (segmentList.size()==0){
            throw new PipeException(ResultEnum.PIPE_NOT_EXIST);
        }
        repository.deleteAll(segmentList);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
