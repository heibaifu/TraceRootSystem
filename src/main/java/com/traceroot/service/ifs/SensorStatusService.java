package com.traceroot.service.ifs;

import com.traceroot.dataobject.SensorStatus;

import java.util.Date;
import java.util.List;

/**
 * 管道状态表service
 * 实现增、改、查
 *
 */
public interface SensorStatusService {

    //查找
    List<SensorStatus> selectBySensorId (String sensorId);

    SensorStatus selectByStatusId (String statusId);

    List<SensorStatus> selectBySensorIdAndStatus (String sensorId,String Status);

    List<SensorStatus> selectByRecordTimeBetween(Date startTime, Date endTime);

    List<SensorStatus> selectBySensorIdAndRecordTimeBetween(String sensorId, Date startTime, Date endTime);

    //增加和修改
    SensorStatus save (SensorStatus status);

    void deleteByStatusId (String statusId);

    void deleteBySensorId (String sensorId);

}
