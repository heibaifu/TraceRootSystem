package com.traceroot.service;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class FaultHistory {

    Date exceptionTime;

    String exceptionSensorId;

    /*这里错误原因指的是传感器的类型，例如：原因为温度传感器，代表温度异常造成的管道故障*/
    String exceptionCause;

    public FaultHistory(Date exceptionTime, String exceptionSensorId, String exceptionCause) {
        this.exceptionTime = exceptionTime;
        this.exceptionSensorId = exceptionSensorId;
        this.exceptionCause = exceptionCause;
    }
}
