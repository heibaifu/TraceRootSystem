package com.traceroot.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 管道传感器类
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class PipelineSensor {

    @Id
    private String sensorId;

    /*对应的管道段id*/
    private String segmentId;

    /*对应管道ID*/
    private String pipeId;

    /*传感器类型*/
    private String typeId;

    /*传感器坐标*/
    private String location;

    /*传感器当前状态*/
    private String presentStatus;

    /*创建时间*/
    @CreatedDate
    @Column(name = "create_time", nullable = false,updatable = false)
    private Date createTime;

    /*更新时间*/
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    public PipelineSensor() {
    }

    @JsonIgnore
    public PipelineSensor(String sensorId, String segmentId, String pipeId, String typeId, String location, String presentStatus) {
        this.sensorId = sensorId;
        this.segmentId = segmentId;
        this.pipeId = pipeId;
        this.typeId = typeId;
        this.location = location;
        this.presentStatus = presentStatus;
    }
}
