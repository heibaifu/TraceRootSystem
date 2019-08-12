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

    /*船只状态*/
    private String status;

    /*记录时间*/
    private Date recordTime;

    public BoatTraceDTO() {
    }

    @JsonIgnore
    public BoatTraceDTO(String traceId, String boatId, String recordLocation, String status) {
        this.traceId = traceId;
        this.boatId = boatId;
        this.traceSerialNumber = traceSerialNumber;
        this.recordLocation = recordLocation;
        this.status = status;
    }
}
