package com.traceroot.service;

import com.traceroot.dataobject.SensorStatus;

/**
 * 管道状态表service
 * 实现增、改、查
 *
 */
public interface SensorStatusService {
    //增加
    SensorStatus save (SensorStatus status);


//    //状态警告
//    String warning (String sensorId);



}
