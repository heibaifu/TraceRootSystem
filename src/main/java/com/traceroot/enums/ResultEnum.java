package com.traceroot.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {

    DELETE_SUCCESS(0,"删除成功"),

    DELETE_FAIL(1,"删除失败"),

    PIPE_SEGMENT_NOT_EXIST(2,"管道段不存在"),

    PIPE_NOT_EXIST(3,"管道段不存在"),

    SENSOR_NOT_EXIST(4,"传感器不存在"),

    SENSOR_TYPE_NOT_EXIST(5,"传感器类型不存在"),

    BOAT_NOT_EXIST(6,"船只不存在"),

    TRACE_NOT_EXIST(7,"轨迹不存在"),

    BOAT_TYPE_NOT_EXIST(8,"船只类型不存在"),

    SEA_ROUTE_NOT_EXIST(9,"航线不存在"),


    ;
    private Integer code;
    private String message;
    ResultEnum (Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
