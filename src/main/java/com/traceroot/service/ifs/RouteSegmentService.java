package com.traceroot.service.ifs;

import com.traceroot.dataobject.RouteSegment;
import com.traceroot.dto.RouteSegmentDTO;

import java.util.List;

public interface RouteSegmentService {

    RouteSegmentDTO selectBySegmentId(String segmentId);

    List<RouteSegmentDTO> selectByRouteId(String routeId);

    List<RouteSegmentDTO> selectByRouteIdOrderBySegmentSerialNumberAsc(String routeId);

    List<RouteSegmentDTO> selectByRouteIdAndSegmentSerialNumberAfter(String routeId, Integer serialNumber);

    List<RouteSegmentDTO> selectAll();

    RouteSegmentDTO insert(RouteSegmentDTO segmentDTO);

    List<RouteSegmentDTO> selectByRouteIdAndStartNearLocation(String routeId ,String location);
/*
    RouteSegment update(RouteSegment segment);
*/

    void deleteBySegmentId(String segmentId);

    void deleteByRouteId(String routeId);

}
