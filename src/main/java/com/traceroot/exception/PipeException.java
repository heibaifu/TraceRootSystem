package com.traceroot.exception;

import com.traceroot.enums.ResultEnum;

/**
 * 对管道操作抛出的异常
 */
public class PipeException extends RuntimeException {
    private Integer code;
    public PipeException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public PipeException(Integer code,String message){
        super(message);
        this.code=code;
    }
}
