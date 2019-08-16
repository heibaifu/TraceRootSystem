package com.traceroot.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * 传感器种类表
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class SensorType {

    @Id
    private String typeId;

    private String typeName;

    private String lowestValue;

    private  String highestValue;

    /*创建时间*/
    @CreatedDate
    @Column(name = "create_time", nullable = false,updatable = false)
    private Date create_time;

    /*更新时间*/
    @LastModifiedDate
    @Column(name = "update_time")
    private Date update_time;

    public SensorType() {
    }

    @JsonIgnore
    public SensorType(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }
}
