package com.traceroot.service;

import com.traceroot.dataobject.SeaRoute;

import java.util.List;

/**
 * 航线表service，实现增删改查
 */
public interface SeaRouteService {

    //todo 按照航线的起点或终点筛选航线

    SeaRoute insert(SeaRoute seaRoute);

    SeaRoute update(SeaRoute seaRoute);

    SeaRoute selectByRouteId(String routeId);

    List<SeaRoute> selectByStatus(String status);

    void deleteByRouteId(String routeId);

    void deleteByStatus(String status);
}
