package com.traceroot.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 每一段管道
 */

@Entity
@Data
@DynamicUpdate
public class PipelineSegment {

    @Id
    private String segmentId;

    /*对应管道ID*/
    private String pipeId;

    /*管道段的序列号*/
    private Integer segmentSerialNumber;

    /*这一段的起点坐标*/
    private String start;

    /*这一段的终点坐标*/
    private String end;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

}
