package com.traceroot.exception;

import com.traceroot.enums.ResultEnum;

/**
 * 管道部分异常
 */
public class PipeException extends TraceRootException {

    private Integer code;

    public PipeException(ResultEnum resultEnum){
        super(resultEnum);
        this.code=resultEnum.getCode();
    }

    public PipeException(Integer code, String message){
        super(code,message);
        this.code=code;
    }
}
