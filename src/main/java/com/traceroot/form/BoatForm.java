package com.traceroot.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoatForm {

    @NotEmpty(message = "船只Id必填")
    private String boatId;

    /*船只类型*/
    @NotEmpty(message = "船只类型必填")
    private String type;

    /*当前位置*/
    @NotEmpty(message = "船只当前位置必填")
    private String presentLocation;

    /*当前船速*//*
    private String speed;

    *//*超速判断，正常为0，超速为1*//*
    private Integer overspeedJudging;

    *//*当前船的方向*//*
    private String direction;*/

    /*船只状态*/
    @NotEmpty(message = "船只状态必填")
    private String status;

    /*船只航线id号*/
    private String routeId;
}
