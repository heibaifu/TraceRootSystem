package com.traceroot.service.ifs;

import com.traceroot.dataobject.RouteSegment;
import com.traceroot.dto.RouteSegmentDTO;

import java.util.List;

public interface RouteSegmentService {

    RouteSegmentDTO selectBySegmentId(String segmentId);

    List<RouteSegmentDTO> selectByRouteId(String routeId);

    List<RouteSegmentDTO> selectByRouteIdOrderBySegmentSerialNumberAsc(String routeId);

    List<RouteSegmentDTO> findByRouteIdAndSegmentSerialNumberAfter(String routeId,Integer serialNumber);

    RouteSegmentDTO insert(RouteSegmentDTO segmentDTO);

/*
    RouteSegment update(RouteSegment segment);
*/

    void deleteBySegmentId(String segmentId);

    void deleteByRouteId(String routeId);

}
