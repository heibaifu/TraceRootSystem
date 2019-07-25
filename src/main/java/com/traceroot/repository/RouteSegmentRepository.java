package com.traceroot.repository;

import com.traceroot.dataobject.RouteSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteSegmentRepository extends JpaRepository<RouteSegment, String> {

    RouteSegment findBySegmentId(String segmentId);

    List<RouteSegment> findByRouteId(String routeId);

    List<RouteSegment> findByRouteIdOrderBySegmentSerialNumber(String routeId);

    Integer countRouteSegmentByRouteId(String routeId);
}
