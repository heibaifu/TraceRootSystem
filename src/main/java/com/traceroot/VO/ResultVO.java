package com.traceroot.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    //错误码code
    private Integer code;

    private String msg;

    //返回的具体内容
    private T data;
}