package com.traceroot.service.impl;

import com.traceroot.converter.dao2dto.PipelineSensor2SensorDTOConverter;
import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dataobject.SensorStatus;
import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineSensorRepository;
import com.traceroot.repository.SensorStatusRepository;
import com.traceroot.service.ifs.PipelineSensorService;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PipelineSensorServiceImpl implements PipelineSensorService {

    @Autowired
    private PipelineSensorRepository repository;

    @Autowired
    private SensorStatusRepository statusRepository;

    /*查询传感器列表DTO*/
    //todo 是否需要给statusList加内容
    @Override
    public List<PipelineSensorDTO> selectBySegmentId(String segmentId) {

        List<PipelineSensorDTO> pipelineSensorDTOS= PipelineSensor2SensorDTOConverter.convert(repository.findBySegmentId(segmentId));

        return pipelineSensorDTOS;
    }

    @Override
    public PipelineSensorDTO selectBySensorId(String sensorId) {

        PipelineSensorDTO pipelineSensorDTO=PipelineSensor2SensorDTOConverter.convert(repository.findBySensorId(sensorId));
        List<SensorStatus>sensorStatuses=statusRepository.findBySensorIdOrderByRecordTimeDesc(pipelineSensorDTO.getSensorId());
        pipelineSensorDTO.setStatusList(sensorStatuses);
        return pipelineSensorDTO;
    }

    @Override
    public List<PipelineSensorDTO> selectByPipeId(String pipeId) {
        List<PipelineSensorDTO> pipelineSensorDTOS=PipelineSensor2SensorDTOConverter.convert(repository.findByPipeId(pipeId));

        return pipelineSensorDTOS;
    }

    @Override
    public PipelineSensorDTO save(PipelineSensorDTO sensorDTO) {

        PipelineSensor result=new PipelineSensor();
        BeanUtils.copyProperties(sensorDTO,result);
        repository.save(result);

        //增加一条传感器状态记录
        SensorStatus sensorStatus = new SensorStatus(RandomUtil.genUniqueId(),result.getSensorId(),result.getPresentStatus());
        statusRepository.save(sensorStatus);

        return sensorDTO;
    }

    //todo 使用了DTO，此方法可以不需要，有待思考
    @Override
    public PipelineSensorDTO updateByStatus(String sensorId, String updateStatus) {

        PipelineSensor pipelineSensor=repository.findBySensorId(sensorId);
        //判断是否存在
        if (pipelineSensor==null){
            throw new PipeException(ResultEnum.SENSOR_NOT_EXIST);
        }
        pipelineSensor.setPresentStatus(updateStatus);
        PipelineSensor result=repository.save(pipelineSensor);
        PipelineSensorDTO pipelineSensorDTO=PipelineSensor2SensorDTOConverter.convert(result);

        //增加一条传感器状态记录
        SensorStatus sensorStatus = new SensorStatus(RandomUtil.genUniqueId(),sensorId,updateStatus);
        statusRepository.save(sensorStatus);
        return pipelineSensorDTO;
    }

    /**
     * 根据传感器状态号查找传感器
     * @param statusEnum
     * @return
     */
    @Override
    public List<PipelineSensorDTO> selectByPresentStatus(SensorStatusEnum statusEnum) {

        List<PipelineSensorDTO> pipelineSensorDTOS=PipelineSensor2SensorDTOConverter.convert(repository.findByPresentStatus(statusEnum.getCode()));
        return pipelineSensorDTOS;

    }

    @Override
    public void deleteBySensorId(String SensorId) {
        PipelineSensor sensor = repository.findBySensorId(SensorId);
        if (sensor==null){
            throw new PipeException(ResultEnum.SENSOR_NOT_EXIST);
        }
        repository.delete(sensor);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
