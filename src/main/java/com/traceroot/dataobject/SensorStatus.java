package com.traceroot.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 传感器状态记录类
 */

@Entity
@Data
@DynamicUpdate
public class SensorStatus {

    @Id
    private String statusId;

    private String sensorId;

    private String status;

    private String recordTime;

}
