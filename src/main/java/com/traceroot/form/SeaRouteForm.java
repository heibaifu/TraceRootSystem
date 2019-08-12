package com.traceroot.form;

import com.traceroot.enums.SeaRouteStatusEnum;
import lombok.Data;

@Data
public class SeaRouteForm {

    private String routeId;

    //航线状态，默认为启用110
    private String status/*= SeaRouteStatusEnum.AVAILABLE.getCode()*/;

    //航线起点
    private String source;

    //航线终点
    private String destination;


}
