package com.traceroot.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.traceroot.dataobject.BoatTrace;
import com.traceroot.enums.BoatStatusEnum;
import com.traceroot.utils.DateToLongSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoatDTO {
    private String boatId;

    /*船只类型*/
    private String type;

    /*当前位置*/
    private String presentLocation;

    /*船只状态*/
    private String status;

    /*船只航线id号*/
    private String routeId;

    /*@JsonSerialize(using= DateToLongSerializer.class)*/
    private Date createTime;

    /*@JsonSerialize(using= DateToLongSerializer.class)*/
    private Date updateTime;

    /*该船对应的船只航线*/
    List<BoatTrace> boatTraces;

    //todo 看之后的需求是否需要在页面显示状态信息，而不是一个code
    /*public BoatStatusEnum getboatStatusEnum(){
        return null;
    }*/
}
