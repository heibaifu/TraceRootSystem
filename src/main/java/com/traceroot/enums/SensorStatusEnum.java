package com.traceroot.enums;


import lombok.Getter;

/**
 * 传感器状态枚举类
 * 字段均为String
 */

@Getter
public enum SensorStatusEnum {

    NORMAL("100","状态正常"),
    BROKEN("101","传感器损坏"),
    ABNORMAL("102","状态异常"),
    ;

    private String code;
    private String message;
    SensorStatusEnum (String code,String message){
        this.code=code;
        this.message=message;
    }
}
