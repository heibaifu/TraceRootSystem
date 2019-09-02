package com.traceroot.repository;

import com.traceroot.dataobject.RouteSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteSegmentRepository extends JpaRepository<RouteSegment, String> {

    RouteSegment findBySegmentId(String segmentId);

    List<RouteSegment> findByRouteId(String routeId);

    List<RouteSegment> findByRouteIdOrderBySegmentSerialNumberAsc(String routeId);

    List<RouteSegment> findByRouteIdAndSegmentSerialNumberAfterOrderBySegmentSerialNumberAsc(String routeId,Integer serialNumber);

    List<RouteSegment> findByRouteIdAndStartIsLike(String routeId ,String location);

    Integer countRouteSegmentByRouteId(String routeId);
}
