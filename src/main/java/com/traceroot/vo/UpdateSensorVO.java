package com.traceroot.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateSensorVO {

    private String code = "2";

    private String sensorId;

    /*对应的管道段id*/
    private String segmentId;

    /*管道状态*/
    private String flag = "0";  //0好，1坏，2未知

    /*管道起点坐标*/
    private String segmentStart;

    /*管道终点坐标*/
    private String segmentEnd;

    /*传感器类型*/
    private String typeId;

    /*传感器坐标*/
    private String sensorLocation;

    /*传感器当前状态*/
    private String presentStatus;

    /*传感器当前值*/
    private String presentValue;

    //    @JsonSerialize(using= DateToLongSerializer.class)
    private Date createTime;    //创建时间，将显示时间进行转换

    //    @JsonSerialize(using= DateToLongSerializer.class)
    private Date updateTime;    //更新时间

}
