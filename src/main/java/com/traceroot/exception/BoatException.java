package com.traceroot.exception;

import com.traceroot.enums.ResultEnum;

/**
 * 船只异常
 */
public class BoatException extends TraceRootException{

    private Integer code;

    public BoatException(ResultEnum resultEnum){
        super(resultEnum);
        this.code=resultEnum.getCode();
    }

    public BoatException(Integer code, String message){
        super(code,message);
        this.code=code;
    }
}
