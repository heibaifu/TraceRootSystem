package com.traceroot.service.ifs;

import com.traceroot.dataobject.RouteSegment;

import java.util.List;

public interface RouteSegmentService {

    RouteSegment selectBySegmentId(String segmentId);

    List<RouteSegment> selectByRouteId(String routeId);

    List<RouteSegment> selectByRouteIdOrderBySegmentSerialNumberAsc(String routeId);

    List<RouteSegment> findByRouteIdAndSegmentSerialNumberAfter(String routeId,Integer serialNumber);

    RouteSegment insert(RouteSegment segment);

    RouteSegment update(RouteSegment segment);

    void deleteBySegmentId(String segmentId);

    void deleteByRouteId(String routeId);

}
