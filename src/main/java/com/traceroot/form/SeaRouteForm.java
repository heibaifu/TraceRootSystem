package com.traceroot.form;

import com.traceroot.enums.SeaRouteStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SeaRouteForm {

    @NotEmpty(message = "航线Id必填")
    private String routeId;

    //航线状态，默认为启用110
    private String status/*= SeaRouteStatusEnum.AVAILABLE.getCode()*/;

    //航线起点
    @NotEmpty(message = "航线起点必填")
    private String source;

    //航线终点
    @NotEmpty(message = "航线终点必填")
    private String destination;


}
