package com.traceroot.form;

import lombok.Data;

@Data
public class BoatForm {
    private String boatId;

    /*船只类型*/
    private String type;

    /*当前位置*/
    private String presentLocation;

    /*当前船速*//*
    private String speed;

    *//*超速判断，正常为0，超速为1*//*
    private Integer overspeedJudging;

    *//*当前船的方向*//*
    private String direction;*/

    /*船只状态*/
    private String status;

    /*船只航线id号*/
    /*private String routeId;*/
}
