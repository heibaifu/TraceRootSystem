package com.traceroot.form;

import lombok.Data;

@Data
public class RouteSegmentForm {

    private String segmentId;

    /*对应航线ID*/
    private String routeId;

    /*航线段的序列号*/
    private Integer segmentSerialNumber;

    /*这一段的起点坐标*/
    private String start;

    /*这一段的终点坐标*/
    private String end;

    /*航道限速*/
    private String limitingSpeed;
}
