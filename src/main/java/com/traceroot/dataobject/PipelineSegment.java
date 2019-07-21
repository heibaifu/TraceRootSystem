package com.traceroot.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.traceroot.dataobject.multikeysclass.PipelineSegmentMultiKeys;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 每一段管道
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@IdClass(PipelineSegmentMultiKeys.class)
public class PipelineSegment implements Serializable {

    @Id
    private String segmentId;

    /*对应管道ID*/
    private String pipeId;

    @Id
    /*管道段的序列号*/
    private Integer segmentSerialNumber;

    /*这一段的起点坐标*/
    private String start;

    /*这一段的终点坐标*/
    private String end;

    /*创建时间*/
    @CreatedDate
    @Column(name = "create_time", nullable = false,updatable = false)
    private Date createTime;

    /*更新时间*/
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    public PipelineSegment(){}

    @JsonIgnore
    public PipelineSegment(String segmentId, String pipeId,  String start, String end) {
        this.segmentId = segmentId;
        this.pipeId = pipeId;
        this.start = start;
        this.end = end;
    }
}
