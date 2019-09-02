package com.traceroot.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RouteSegmentForm {

    @NotEmpty(message = "管道段Id必填")
    private String segmentId;

    /*对应航线ID*/
    @NotEmpty(message = "航线Id必填")
    private String routeId;

    /*这一段的起点坐标*/
    @NotEmpty(message = "起点坐标必填")
    private String start;

    /*这一段的终点坐标*/
    @NotEmpty(message = "终点坐标必填")
    private String end;

    /*航道限速*/
    private String limitingSpeed;
}
