package com.traceroot.enums;


import lombok.Getter;

/**
 * 传感器状态枚举类
 */

@Getter
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
