package com.traceroot.service.ifs;

import com.traceroot.dataobject.SeaRoute;
import com.traceroot.dto.SeaRouteDTO;

import java.util.List;

/**
 * 航线表service，实现增删改查
 */
public interface SeaRouteService {

    //todo 按照航线的起点或终点筛选航线

    SeaRouteDTO insert(SeaRouteDTO seaRouteDTO);

/*
    SeaRouteDTO update(SeaRouteDTO seaRouteDTO);
*/

    SeaRouteDTO selectByRouteId(String routeId);

    List<SeaRouteDTO> selectByStatus(String status);

    List<SeaRouteDTO> selectAll();

    void deleteByRouteId(String routeId);

    void deleteByStatus(String status);
}
