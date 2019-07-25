package com.traceroot.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.traceroot.enums.SeaRouteStatusEnum;
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
 * 航线表
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class SeaRoute {

    //航线id
    @Id
    private String routeId;

    //航线状态，默认为启用110
    private String status= SeaRouteStatusEnum.AVAILABLE.getCode();

    //航线起点
    private String source;

    //航线终点
    private String destination;

    public SeaRoute() {
    }

    @JsonIgnore
    public SeaRoute(String routeId, String status, String source, String destination) {
        this.routeId = routeId;
        this.status = status;
        this.source = source;
        this.destination = destination;
    }

    /*创建时间*/
    @CreatedDate
    @Column(name = "create_time", nullable = false,updatable = false)
    private Date createTime;

    /*更新时间*/
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;
}
