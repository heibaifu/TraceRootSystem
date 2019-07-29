package com.traceroot.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * 船只轨迹类，对应每一段船只轨迹
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class BoatTrace {

    /*轨迹编号*/
    @Id
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
    @CreatedDate
    @Column(name = "record_time", nullable = false,updatable = false)
    private Date recordTime;

    public BoatTrace() {
    }

    @JsonIgnore
    public BoatTrace(String traceId, String boatId, String recordLocation, String status) {
        this.traceId = traceId;
        this.boatId = boatId;
        this.recordLocation = recordLocation;
        this.status = status;
    }

    @JsonIgnore
    public Integer traceSerialNumber2Int(){
        return Integer.valueOf(traceSerialNumber);
    }
}
