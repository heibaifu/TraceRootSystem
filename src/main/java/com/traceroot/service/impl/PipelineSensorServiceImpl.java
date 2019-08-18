package com.traceroot.service.impl;

import com.traceroot.converter.dao2dto.PipelineSensor2SensorDTOConverter;
import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dataobject.SensorStatus;
import com.traceroot.dataobject.SensorType;
import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.dto.SensorTypeDTO;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineSensorRepository;
import com.traceroot.repository.SensorStatusRepository;
import com.traceroot.service.ifs.PipelineSensorService;
import com.traceroot.service.ifs.SensorStatusService;
import com.traceroot.service.ifs.SensorTypeService;
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
    private SensorTypeService sensorTypeService;

    @Autowired
    private SensorStatusService statusService;

    @Override
    public List<PipelineSensorDTO> selectAll() {
        List<PipelineSensor> sensorList = repository.findAll();
        List<PipelineSensorDTO> sensorDTOList = PipelineSensor2SensorDTOConverter.convert(sensorList);
        return sensorDTOList;
    }

    /*查询传感器列表DTO*/
    @Override
    public List<PipelineSensorDTO> selectBySegmentId(String segmentId) {

        List<PipelineSensorDTO> pipelineSensorDTOS= PipelineSensor2SensorDTOConverter.convert(repository.findBySegmentId(segmentId));

        return pipelineSensorDTOS;
    }

    @Override
    public PipelineSensorDTO selectBySensorId(String sensorId) {

        PipelineSensorDTO pipelineSensorDTO=PipelineSensor2SensorDTOConverter.convert(repository.findBySensorId(sensorId));
        if (pipelineSensorDTO == null){
            return pipelineSensorDTO;
        }
        List<SensorStatus>sensorStatuses=statusService.selectBySensorId(pipelineSensorDTO.getSensorId());
        pipelineSensorDTO.setStatusList(sensorStatuses);
        return pipelineSensorDTO;
    }

    @Override
    public List<PipelineSensorDTO> selectByPipeId(String pipeId) {
        List<PipelineSensorDTO> pipelineSensorDTOS=PipelineSensor2SensorDTOConverter.convert(repository.findByPipeId(pipeId));

        return pipelineSensorDTOS;
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
    public PipelineSensorDTO save(PipelineSensorDTO sensorDTO) {

        PipelineSensor pipelineSensor=new PipelineSensor();
        BeanUtils.copyProperties(sensorDTO,pipelineSensor);

        //增加一条传感器状态记录
        //先判断传感器是否有状态值（presentValue），若有状态值则判断，没有状态值直接设置状态号，
        SensorStatus sensorStatus = new SensorStatus();

        SensorTypeDTO sensorTypeDTO=sensorTypeService.selectByTypeId(pipelineSensor.getTypeId());

        if (pipelineSensor.getPresentStatus().equals(SensorStatusEnum.BROKEN.getCode())){
            pipelineSensor.setPresentValue(null);
        } else if (pipelineSensor.getPresentValue()!=null){
            if (Double.valueOf(pipelineSensor.getPresentValue()) >= Double.valueOf(sensorTypeDTO.getLowestValue())&&Double.valueOf(pipelineSensor.getPresentValue()) <= Double.valueOf(sensorTypeDTO.getHighestValue())){
                pipelineSensor.setPresentStatus(SensorStatusEnum.NORMAL.getCode());
            }else {
                pipelineSensor.setPresentStatus(SensorStatusEnum.ABNORMAL.getCode());
            }
        }

        PipelineSensor result=repository.save(pipelineSensor);
        PipelineSensorDTO pipelineSensorDTO=PipelineSensor2SensorDTOConverter.convert(result);


        sensorStatus.setStatusId(RandomUtil.genUniqueId());
        sensorStatus.setSensorId(result.getSensorId());
        sensorStatus.setStatus(result.getPresentStatus());
        sensorStatus.setValue(result.getPresentValue());

        statusService.save(sensorStatus);

        return pipelineSensorDTO;
    }

    /*@Override
    public PipelineSensorDTO update(PipelineSensorDTO pipelineSensorDTO) {

        PipelineSensor pipelineSensor=repository.findBySensorId(pipelineSensorDTO.getSensorId());
        //判断是否存在
        if (pipelineSensor==null){
            throw new PipeException(ResultEnum.SENSOR_NOT_EXIST);
        }

        BeanUtils.copyProperties(pipelineSensorDTO,pipelineSensor);
        PipelineSensor result=repository.save(pipelineSensor);

        //增加一条传感器状态记录
        SensorStatus sensorStatus = new SensorStatus();
        sensorStatus.setStatusId(RandomUtil.genUniqueId());
        sensorStatus.setSensorId(result.getSensorId());

        if (result.getPresentValue()==null) {
            sensorStatus.setStatus(result.getPresentStatus());
        }else {
            sensorStatus.setValue(result.getPresentValue());
        }
        statusService.save(sensorStatus);

        return pipelineSensorDTO;
    }*/

    @Override
    public void deleteBySensorId(String SensorId) {
        PipelineSensor sensor = repository.findBySensorId(SensorId);
        if (sensor==null){
            throw new PipeException(ResultEnum.SENSOR_NOT_EXIST);
        }
        repository.delete(sensor);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }

    @Override
    public PipelineSensorDTO selectByLocation(String location) {
        PipelineSensor pipelineSensor=repository.findByLocation(location);
        PipelineSensorDTO pipelineSensorDTO=PipelineSensor2SensorDTOConverter.convert(pipelineSensor);
        return pipelineSensorDTO;
    }
}
