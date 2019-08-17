package com.traceroot.vo;

import com.traceroot.dto.BoatTraceDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UpdateBoatVO implements Serializable {

    private static final long serialVersionUID = 1780457570498248057L;

    private String code = "0";    //区分是新船(1)还是旧船(0)

    private String boatId;

    /*船只类型*/
    private String type;

    /*当前位置*/
    private String presentLocation;

    /*之前的位置*/
    private String lastLocation;

    /*船只状态*/
    private String status;

    /*船只航线id号*/
    private String routeId;

    /*@JsonSerialize(using= DateToLongSerializer.class)*/
    private Date createTime;

    /*@JsonSerialize(using= DateToLongSerializer.class)*/
    private Date updateTime;

}
