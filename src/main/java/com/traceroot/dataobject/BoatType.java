package com.traceroot.dataobject;

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
 * 船只种类表
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class BoatType {

    public BoatType(){}

    public BoatType(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    /*船只种类id*/
    @Id
    private String typeId;

    /*船只种类名称*/
    private String typeName;

    /*创建时间*/
    @CreatedDate
    @Column(name = "create_time", nullable = false,updatable = false)
    private Date createTime;

    /*更新时间*/
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

}
