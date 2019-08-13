package com.traceroot.dto;

import com.traceroot.dataobject.RouteSegment;
import com.traceroot.enums.SeaRouteStatusEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SeaRouteDTO {

    private String routeId;

    //航线状态，默认为启用110
    private String status= SeaRouteStatusEnum.AVAILABLE.getCode();

    //航线起点
    private String source;

    //航线终点
    private String destination;

    private Date createTime;

    /*更新时间*/
    private Date updateTime;

    /*此航线所包含的航线段*/
    List<RouteSegmentDTO> routeSegments;

}
