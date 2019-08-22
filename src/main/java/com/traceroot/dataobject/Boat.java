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
 * 船只类
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Boat {

    @Id
    private String boatId;

    /*船只类型*/
    private String type;

    /*当前位置*/
    private String presentLocation;

    /*当前船速*/
    private String speed;

    /*超速判断，正常为0，超速为1*/
    private Integer overspeedJudging;

    /*当前船的方向*/
    private String direction;

    /*船只状态*/
    private String status;

    /*船只航线id号*/
    private String routeId;

    /*创建时间*/
    @CreatedDate
    @Column(name = "create_time", nullable = false,updatable = false)
    private Date createTime;

    /*更新时间*/
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    public Boat() {
    }

    /**
     * 带航线的构造方法
     * @param boatId
     * @param type
     * @param presentLocation
     * @param status
     * @param routeId
     */
    @JsonIgnore
    public Boat(String boatId, String type, String presentLocation, String status, String routeId) {
        this.boatId = boatId;
        this.type = type;
        this.presentLocation = presentLocation;
        this.status = status;
        this.routeId = routeId;
    }

    /**
     * 不带航线的构造方法
     * @param boatId
     * @param type
     * @param presentLocation
     * @param status
     */
    @JsonIgnore
    public Boat(String boatId, String type, String presentLocation, String status) {
        this.boatId = boatId;
        this.type = type;
        this.presentLocation = presentLocation;
        this.status = status;
    }
}
