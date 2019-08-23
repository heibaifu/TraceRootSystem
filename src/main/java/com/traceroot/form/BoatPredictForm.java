package com.traceroot.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoatPredictForm {

    @NotEmpty(message = "Id必填")
    private String predictId;

    /*船只编号*/
    @NotEmpty(message = "boatId必填")
    private String boatId;

    /*预测到达地点*/
    @NotEmpty(message = "predictLocation必填")
    private String predictLocation;

    /*预测到达时间*/
    @NotEmpty(message = "arriveTime必填")
    private String arriveTime;

    /*预测到达该地点的概率*/
    @NotEmpty(message = "probability必填")
    private String probability;

  /*  *//*创建时间*//*
    private String create_time;*/
}
