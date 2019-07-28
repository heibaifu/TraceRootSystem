package com.traceroot.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

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

//    @Column(name = "status"/*, nullable = false*/,columnDefinition="varchar(10) default '100'")
    //@ColumnDefault("100")
    private String status = "100";

    private String value;

    /*记录时间*/
    @CreatedDate
    @Column(name = "record_time", nullable = false,updatable = false)
    private Date recordTime;

//    @LastModifiedDate
//    @Column(name = "record_time")
//    private String recordTime;
    @JsonIgnore
    public SensorStatus(String statusId,String sensorId,String status){
        this.sensorId=sensorId;
        this.status=status;
        this.statusId=statusId;
    };

    public SensorStatus(){}

}
