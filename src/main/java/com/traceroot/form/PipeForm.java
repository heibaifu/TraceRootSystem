package com.traceroot.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * pipe表单验证
 */

@Data
public class PipeForm {

    @NotEmpty(message = "Id必填")
    private String pipeId;

    @NotEmpty(message = "源地址必填")
    private String source;

    @NotEmpty(message = "目的地址必填")
    private String destination;

    private String createTime;

}
