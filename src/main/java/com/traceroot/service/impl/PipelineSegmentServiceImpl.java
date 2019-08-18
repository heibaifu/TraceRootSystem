package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineSegmentRepository;
import com.traceroot.service.ifs.PipelineSegmentService;
import com.traceroot.converter.dao2dto.PipelineSegment2PipeSegmentDTOConverter;
import com.traceroot.service.ifs.PipelineSensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PipelineSegmentServiceImpl implements PipelineSegmentService {

    @Autowired
    private PipelineSegmentRepository repository;

    @Autowired
    private PipelineSensorService pipelineSensorService;

    //将查出来的列表转换为DTO列表
    @Override
    public List<PipeSegmentDTO> selectAll() {
        List<PipelineSegment> segmentList = repository.findAll();
        List<PipeSegmentDTO> segmentDTOList = PipelineSegment2PipeSegmentDTOConverter.convert(segmentList);
        return segmentDTOList;
    }

    @Override
    public PipeSegmentDTO selectBySegmentId(String segmentId) {
        return PipelineSegment2PipeSegmentDTOConverter.convert(repository.findBySegmentId(segmentId));
    }

    @Override
    public List<PipeSegmentDTO> selectByPipeId(String pipeId) {
        return PipelineSegment2PipeSegmentDTOConverter.convert(repository.findByPipeIdOrderBySegmentSerialNumber(pipeId));
    }

    /**
     * 返回有问题的管道段
     * @return
     */
    public List<PipeSegmentDTO> selectBySensorStatus(SensorStatusEnum statusEnum) {

        List<PipelineSensorDTO> pipelineSensors=pipelineSensorService.selectByPresentStatus(statusEnum);
        List<PipelineSegment> segmentList =new ArrayList<>();
        for (int i=0;i<pipelineSensors.size();i++){
            //从传感器列表的第0项开始查找
            String segmentId=pipelineSensors.get(i).getSegmentId();
            segmentList.add(repository.findBySegmentId(segmentId));
        }

        List<PipelineSegment> collect = segmentList.stream().distinct().collect(Collectors.toList());

        List<PipeSegmentDTO> segmentDTOList = PipelineSegment2PipeSegmentDTOConverter.convert(collect);
        return segmentDTOList;
    }



    /**
     * 判断管道状态
     * @param segmentId
     * @return 0正常 1异常
     */
    @Override
    public Integer testifyStatus(String segmentId) {
        List<PipelineSensorDTO> pipelineSensorDTOS = pipelineSensorService.selectBySegmentId(segmentId);
        for (int i = 0; i < pipelineSensorDTOS.size(); i++) {
            if (!pipelineSensorDTOS.get(i).getPresentStatus().equals(SensorStatusEnum.NORMAL.getCode()))
                return 1;
        }
        return 0;
    }

    /**
     * 插入
     * todo 插入到中间部分，需要修改序列号，可以以后有需求再写
     * @param pipeSegmentDTO
     * @return
     */
    @Override
    public PipelineSegment insert(PipeSegmentDTO pipeSegmentDTO) {

        //判断是否已经存在
        PipelineSegment pipelineSegment = repository.findBySegmentId(pipeSegmentDTO.getSegmentId());
        if (pipelineSegment!=null){
            throw new PipeException(ResultEnum.PIPE_SEGMENT_AlREADY_EXIST);
        }

        //不存在则插入
        pipelineSegment = new PipelineSegment();
        BeanUtils.copyProperties(pipeSegmentDTO,pipelineSegment);
        Integer amount = repository.countPipelineSegmentByPipeId(pipeSegmentDTO.getPipeId());
        pipelineSegment.setSegmentSerialNumber(amount+1);
        return repository.save(pipelineSegment);
    }

    /**
     * 更新
     * todo 调整管道序号（等价于删除再插入），可以以后有需求再写
     * @param pipeSegmentDTO
     * @return
     */
    @Override
    public PipelineSegment update(PipeSegmentDTO pipeSegmentDTO) {

        //判断是否已经存在
        PipelineSegment pipelineSegment = repository.findBySegmentId(pipeSegmentDTO.getSegmentId());
        if (pipelineSegment==null){
            throw new PipeException(ResultEnum.PIPE_SEGMENT_NOT_EXIST);
        } else {
            //存在则更新
            pipeSegmentDTO.setSegmentSerialNumber(pipelineSegment.getSegmentSerialNumber());
            BeanUtils.copyProperties(pipeSegmentDTO,pipelineSegment);
            return repository.save(pipelineSegment);
        }
    }

    //查找在serialNumber之后的部分
    @Override
    public List<PipelineSegment> selectByPipeIdAndSegmentSerialNumberAfter(String pipeId, Integer serialNumber) {

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
