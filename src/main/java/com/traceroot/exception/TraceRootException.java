package com.traceroot.exception;

import com.traceroot.enums.ResultEnum;

/**
 * 项目异常
 */

public class TraceRootException extends RuntimeException {
    private Integer code;
    public TraceRootException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public TraceRootException(Integer code, String message){
        super(message);
        this.code=code;
    }
}
