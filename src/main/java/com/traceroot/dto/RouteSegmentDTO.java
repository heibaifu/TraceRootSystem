package com.traceroot.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RouteSegmentDTO {

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
    private Date createTime;

    /*更新时间*/
    private Date updateTime;
}
