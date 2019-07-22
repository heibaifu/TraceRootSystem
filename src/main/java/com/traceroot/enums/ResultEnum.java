package com.traceroot.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {

    DELETE_SUCCESS(0,"删除成功"),

    DELETE_FAIL(1,"删除失败"),

    PIPE_SEGMENT_NOT_EXIST(2,"管道段不存在"),

    PIPE_NOT_EXIST(3,"管道段不存在"),

    SENSOR_NOT_EXIST(4,"传感器不存在"),
    ;
    private Integer code;
    private String message;
    ResultEnum (Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
