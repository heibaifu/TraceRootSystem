package com.traceroot.dto;

import lombok.Data;

@Data
public class PipeDTO {

    private String pipeId;

    /*管道起点*/
    private Double[] source = new Double[2];

    /*管道终点*/
    private Double[] destination = new Double[2];

}
