package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dataobject.SensorStatus;
import com.traceroot.dataobject.SensorType;
import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.SensorStatusRepository;
import com.traceroot.service.ifs.SensorStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SensorStatusServiceImpl implements SensorStatusService {

    @Autowired
    private SensorStatusRepository repository;

    @Autowired
    private SensorTypeServiceImpl sensorTypeService;

    @Autowired
    private PipelineSensorServiceImpl pipelineSensorService;


    @Override
    public SensorStatus save(SensorStatus status) {

        PipelineSensorDTO pipelineSensorDTO=pipelineSensorService.selectBySensorId(status.getSensorId());

        SensorType sensorType=sensorTypeService.selectByTypeId(pipelineSensorDTO.getTypeId());

        if (status.getValue()!=null){
            if (Double.valueOf(status.getValue()) >= Double.valueOf(sensorType.getLowestValue())&&Double.valueOf(status.getValue()) <= Double.valueOf(sensorType.getHighestValue())){
                status.setStatus(SensorStatusEnum.NORMAL.getCode());
            }else {
                status.setStatus(SensorStatusEnum.ABNORMAL.getCode());
            }
        }
        return repository.save(status);
    }

    /**
     * 按照sensorId降序返回
     * @param sensorId
     * @return
     */
    @Override
    public List<SensorStatus> selectBySensorId(String sensorId) {
        return repository.findBySensorIdOrderByRecordTimeDesc(sensorId);
    }

    /**
     * 按照statusId返回
     * @param statusId
     * @return
     */
    @Override
    public SensorStatus selectByStatusId(String statusId) {
        return repository.findByStatusId(statusId);
    }

    /**
     * 按照时间区间查找
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<SensorStatus> selectByRecordTimeBetween(Date startTime, Date endTime) {
        return repository.findByRecordTimeBetweenOrderByRecordTime(startTime,endTime);
    }

    @Override
    public List<SensorStatus> selectBySensorIdAndRecordTimeBetween(String sensorId, Date startTime, Date endTime) {
        return repository.findBySensorIdAndRecordTimeBetweenOrderByRecordTimeDesc(sensorId,startTime,endTime);
    }

    /**
     * 按照状态id进行删除
     * @param statusId
     */
    @Override
    public void deleteByStatusId(String statusId) {
        SensorStatus byStatusId = repository.findByStatusId(statusId);
        if (byStatusId==null){
            throw new PipeException(ResultEnum.PIPE_NOT_EXIST);
        }
        repository.delete(byStatusId);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }

    /**
     * 按照传感器id进行删除
     * @param sensorId
     */
    @Override
    public void deleteBySensorId(String sensorId) {
        List<SensorStatus> sensorStatusList = repository.findBySensorIdOrderByRecordTimeDesc(sensorId);
        if (sensorStatusList.size()==0){
            throw new PipeException(ResultEnum.SENSOR_NOT_EXIST);
        }
        repository.deleteAll(sensorStatusList);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
