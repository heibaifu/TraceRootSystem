package com.traceroot.form;

import lombok.Data;

@Data
public class BoatForm {
    private String boatId;

    /*船只类型*/
    private String type;

    /*当前位置*/
    private String presentLocation;

    /*船只状态*/
    private String status;

    /*船只航线id号*/
    private String routeId;
}
