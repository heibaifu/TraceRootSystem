package com.traceroot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class PipeDTO {

    private String pipeId;

    /*管道起点*/
    private String source;

    /*管道终点*/
    private String destination;

    /*private Date createTime;*/

    public PipeDTO() {
    }

    @JsonIgnore
    public PipeDTO(String pipeId, String source, String destination/*, Date createTime*/) {
        this.pipeId = pipeId;
        this.source = source;
        this.destination = destination;
        /*this.createTime = createTime;*/
    }
}
