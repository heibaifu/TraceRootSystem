package com.traceroot.dto;

import com.traceroot.dataobject.SensorStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PipeSegmentDTO {

    private String segmentId;

    /*对应管道ID*/
    private String pipeId;

    /*管道段的序列号*/
    private Integer segmentSerialNumber;

    /*管道起点*/
    private String start;

    /*管道终点*/
    private String end;

    /*管道段对应的传感器状态，可以不传*/
    private List<SensorStatus> statusList = null;

    private Date createTime;

    private Date updateTime;

}
