package com.traceroot.exception;

import com.traceroot.enums.ResultEnum;

/**
 * 航线异常
 */
public class RouteException extends TraceRootException {

    private Integer code;

    public RouteException(ResultEnum resultEnum){
        super(resultEnum);
        this.code=resultEnum.getCode();
    }

    public RouteException(Integer code, String message){
        super(code,message);
        this.code=code;
    }
}
