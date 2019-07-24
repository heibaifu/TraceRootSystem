package com.traceroot.service;

import com.traceroot.dataobject.SensorStatus;

import java.util.List;

/**
 * 管道状态表service
 * 实现增、改、查
 *
 */
public interface SensorStatusService {
    //增加
    SensorStatus save (SensorStatus status);
    //查找
    List<SensorStatus> selectBySensorId (String sensorId);

   // List<SensorStatus> todo 按时间区间查找

    SensorStatus selectByStatusId (String statusId);

    //删除 todo 实现还没写
    void deleteByStatusId (String statusId);

    void deleteBySensorId (String sensorId);
}
