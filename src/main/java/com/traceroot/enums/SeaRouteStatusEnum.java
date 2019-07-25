package com.traceroot.enums;

import lombok.Getter;

@Getter
public enum SeaRouteStatusEnum {
    AVAILABLE("110","启用"),
    DISABLED("111","未启用"),
    CANCLE("112","航线已取消"),
    ;

    private String code;
    private String message;

    SeaRouteStatusEnum (String code,String message) {
        this.code = code;
        this.message = message;
    }
}
