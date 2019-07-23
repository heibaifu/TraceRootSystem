package com.traceroot.dataobject.exception;

import com.traceroot.enums.ResultEnum;

public class BoatException extends RuntimeException {
    private Integer code;
    public BoatException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public BoatException(Integer code,String message){
        super(message);
        this.code=code;
    }
}
