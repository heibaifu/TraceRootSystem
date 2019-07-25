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
 * 航线分段，每一条航线对应多段航线段
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class RouteSegment {

    @Id
    private String segmentId;

    /*对应航线ID*/
    private String routeId;

    /*航线段的序列号*/
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

    public RouteSegment(){}

    @JsonIgnore
    public RouteSegment(String segmentId, String routeId,  String start, String end) {
        this.segmentId = segmentId;
        this.routeId = routeId;
        this.start = start;
        this.end = end;
    }
}
