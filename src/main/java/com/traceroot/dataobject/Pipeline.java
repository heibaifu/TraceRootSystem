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
 * 管道类
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Pipeline {

    @Id
    private String pipeId;

    /*管道起点*/
    private String source;

    /*管道终点*/
    private String destination;

    /*创建时间*/
    @CreatedDate
    @Column(name = "create_time", nullable = false,updatable = false)
    private Date createTime;

    /*更新时间*/
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    public Pipeline(){}

    @JsonIgnore
    public Pipeline(String pipeId, String source, String destination) {
        this.pipeId = pipeId;
        this.source = source;
        this.destination = destination;
    }
}
