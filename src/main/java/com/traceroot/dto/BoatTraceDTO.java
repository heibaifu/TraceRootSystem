package com.traceroot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class BoatTraceDTO {

    /*轨迹编号*/
    private String traceId;

    /*船只编号*/
    private String boatId;

    /*轨迹序列号*/
    private String traceSerialNumber;

    /*记录位置*/
    private String recordLocation;

    /*记录船速*/
    private String speed;

    /*超速判断，正常为0，超速为1*/
    private Integer overspeedJudging;

    /*记录船方向*/
    private String direction;

    /*船只状态*/
    private String status;

    /*记录时间*/
    private Date recordTime;

    public BoatTraceDTO() {
    }

    //测试用
    @JsonIgnore
    public BoatTraceDTO(String traceId, String boatId, String recordLocation, String direction, String status) {
        this.traceId = traceId;
        this.boatId = boatId;
        this.direction = direction;
        this.traceSerialNumber = traceSerialNumber;
        this.recordLocation = recordLocation;
        this.status = status;
    }

    @JsonIgnore
    public BoatTraceDTO(String traceId, String boatId, String traceSerialNumber, String recordLocation, String speed, Integer overspeedJudging, String direction, String status) {
        this.traceId = traceId;
        this.boatId = boatId;
        this.traceSerialNumber = traceSerialNumber;
        this.recordLocation = recordLocation;
        this.speed = speed;
        this.overspeedJudging = overspeedJudging;
        this.direction = direction;
        this.status = status;
    }
}
