package com.traceroot.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 传给前端的传感器数据
 */

@Data
public class SensorVO {

    /*传感器种类*/
    private String type;

    /*传感器的值*/
    private String value;

    /*传感器种类名*/
    private String name;

    @JsonIgnore
    public SensorVO(String type, String value, String name) {
        this.type = type;
        this.value = value;
        this.name = name;
    }

    public SensorVO() {
    }
}
