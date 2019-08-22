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
 * 管道段寿命预测表
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class PipelineSegmentPredict {

    @Id
    private String predictId;

    private String segmentId;

    private String predictLife;

    /*创建时间*/
    @CreatedDate
    @Column(name = "create_time", nullable = false,updatable = false)
    private Date createTime;

}
