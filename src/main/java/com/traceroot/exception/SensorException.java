package com.traceroot.exception;

import com.traceroot.enums.ResultEnum;

public class SensorException extends RuntimeException {
    private Integer code;
    public SensorException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public SensorException(Integer code,String message){
        super(message);
        this.code=code;
    }
}
