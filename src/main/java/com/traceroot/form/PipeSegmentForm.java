package com.traceroot.form;

import com.traceroot.dataobject.SensorStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * PipeSegment表单验证
 */

@Data
public class PipeSegmentForm {

    @NotEmpty(message = "段Id必填")
    private String segmentId;

    /*对应管道ID*/
    @NotEmpty(message = "管道Id必填")
    private String pipeId;

    /*管道段的序列号，可以不传*/
    private Integer segmentSerialNumber;

    /*管道起点*/
    @NotEmpty(message = "源地址必填")
    private String start;

    /*管道终点*/
    @NotEmpty(message = "目的地址必填")
    private String end;

    /*管道段对应的传感器状态，可以不传*/
    private String statusList = null;

}
