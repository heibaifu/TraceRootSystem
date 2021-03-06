package com.traceroot.service.ifs;

import com.traceroot.dataobject.Boat;
import com.traceroot.dataobject.BoatTrace;
import com.traceroot.dataobject.RouteSegment;
import com.traceroot.dto.RouteSegmentDTO;

import java.util.*;

/**
 * 交叉业务接口
 */
public interface CrossService {

    NavigableMap<Integer,List<String>> selectByPassingPipelineSegment(String segmentId, Date startTime, Date endTime, Integer accuracyDegree);

    Map<String,List<BoatTrace>> findBoatNearSegmentDuringTime(String segmentId, Date startTime, Date endTime , Integer accuracyDegree);

    RouteSegmentDTO matchBoatAndRouteSegment(String routeID, String boatLocation);

    Integer ditermineOverspeed(String routeID, String boatLocation, String boatSpeed);
}
