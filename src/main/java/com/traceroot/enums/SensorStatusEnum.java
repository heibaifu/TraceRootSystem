package com.traceroot.enums;

/**
 *
 */
public enum SensorStatusEnum {

    NOMAL(100,"状态正常"),
    ;

    private Integer code;
    private String message;
    SensorStatusEnum (Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
