package com.traceroot.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.traceroot.dataobject.SensorStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UpdatePipeSegmentVO {

    private String code = "3";

    private String segmentId;

    /*对应管道ID*/
    private String pipeId;

    /*管道段的序列号*/
    private Integer segmentSerialNumber;

    /*管道起点*/
    private String start;

    /*管道终点*/
    private String end;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public UpdatePipeSegmentVO() {
    }

    @JsonIgnore
    public UpdatePipeSegmentVO(String segmentId, String pipeId, Integer segmentSerialNumber, String start, String end) {
        this.segmentId = segmentId;
        this.pipeId = pipeId;
        this.segmentSerialNumber = segmentSerialNumber;
        this.start = start;
        this.end = end;
    }

}
