package com.traceroot.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 管道类
 */

@Entity
@Data
@DynamicUpdate  //自动更新注解
public class Pipeline {

    @Id
    private String pipeId;

    /*管道起点*/
    private String source;

    /*管道终点*/
    private String destination;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

    @JsonIgnore
    public Pipeline(String pipeId, String source, String destination) {
        this.pipeId = pipeId;
        this.source = source;
        this.destination = destination;
    }
}
