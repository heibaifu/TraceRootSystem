package com.traceroot.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoatPredictForm {

    private String predict_id;

    /*船只编号*/
    private String boat_id;

    /*预测到达地点*/
    private String predict_location;

    /*预测到达时间*/
    private String arrive_time;

    /*预测到达该地点的概率*/
    private String probability;

  /*  *//*创建时间*//*
    private String create_time;*/
}
