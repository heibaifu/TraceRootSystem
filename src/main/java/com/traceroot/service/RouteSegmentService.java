package com.traceroot.service;

import com.traceroot.dataobject.RouteSegment;

import java.util.List;

public interface RouteSegmentService {

    RouteSegment selectBySegmentId(String segmentId);

    List<RouteSegment> selectByRouteId(String routeId);

    List<RouteSegment> selectByRouteIdOrderBySegmentSerialNumberAsc(String routeId);

    RouteSegment insert(RouteSegment segment);

    RouteSegment update(RouteSegment segment);

    void deleteBySegmentId(String segmentId);

    void deleteByRouteId(String routeId);

}
