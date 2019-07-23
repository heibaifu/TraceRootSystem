package com.traceroot.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

/**
 * 传感器状态记录类
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class SensorStatus {

    @Id
    private String statusId;

    private String sensorId;

    private String status;

    @LastModifiedDate
    @Column(name = "record_time")
    private String recordTime;

    public SensorStatus(String statusId,String sensorId,String status){
        this.sensorId=sensorId;
        this.status=status;
        this.statusId=statusId;
    };

}
