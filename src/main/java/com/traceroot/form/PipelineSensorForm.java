package com.traceroot.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 管道传感器表单验证
 */

@Data
public class PipelineSensorForm {

    private String sensorId;

    /*对应的管道段id*/
    @NotEmpty(message = "段Id必填")
    private String segmentId;

    /*对应管道ID*/
    @NotEmpty(message = "管道Id必填")
    private String pipeId;

    /*传感器类型*/
    @NotEmpty(message = "类型Id必填")
    private String typeId;

    /*传感器坐标*/
    @NotEmpty(message = "位置必填")
    private String location;

    /*传感器当前状态*/
    private String presentStatus;

    /*传感器当前值可以为空*/
    private String presentValue=null;
}
