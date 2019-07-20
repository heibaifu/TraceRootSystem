package com.traceroot.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 管道传感器类
 */

@Entity
@Data
@DynamicUpdate
public class PipelineSensor {

    @Id
    private String sensorId;

    /*对应的管道段id*/
    private String segmentId;

    /*对应管道ID*/
    private String pipeId;

    /*传感器类型*/
    private String type;

    /*传感器坐标*/
    private String location;

    /*传感器当前状态*/
    private String presentStatus;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;
}
