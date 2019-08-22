package com.traceroot.dataobject;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * 船只预测表
 * 预测船只的下一个位置及概率
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class BoatPredict {
    @Id
    private String predictId;

    /*船只编号*/
    private String boatId;

    /*预测到达地点*/
    private String predictLocation;

    /*预测到达时间*/
    private String arriveTime;

    /*预测到达该地点的概率*/
    private String probability;

    /*创建时间*/
    @CreatedDate
    @Column(name = "create_time", nullable = false,updatable = false)
    private Date createTime;

}
