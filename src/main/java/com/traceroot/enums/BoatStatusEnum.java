package com.traceroot.enums;

import lombok.Getter;

@Getter
public enum BoatStatusEnum {

    NOMAL(150,"状态正常"),
    DANGEROUS(151,"状态危险"),
            ;

    private Integer code;
    private String message;

    BoatStatusEnum (Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
