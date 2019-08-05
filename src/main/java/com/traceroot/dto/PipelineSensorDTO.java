package com.traceroot.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.traceroot.dataobject.SensorStatus;
import com.traceroot.utils.DateToLongSerializer;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data

public class PipelineSensorDTO {

    private String sensorId;

    /*对应的管道段id*/
    private String segmentId;

    /*对应管道ID*/
    private String pipeId;

    /*传感器类型*/
    private String typeId;

    /*传感器坐标*/
    private String location;

    /*传感器当前状态*/
    private String presentStatus;

    /*传感器当前值*/
    private String presentValue;

//    @JsonSerialize(using= DateToLongSerializer.class)
    private Date createTime;    //创建时间，将显示时间进行转换

//    @JsonSerialize(using= DateToLongSerializer.class)
    private Date updateTime;    //更新时间

    /*每个传感器有自己的历史状态记录表*/
    List<SensorStatus> statusList;

}
