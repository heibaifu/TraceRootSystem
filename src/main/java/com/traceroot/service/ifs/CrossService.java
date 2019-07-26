package com.traceroot.service.ifs;

import com.traceroot.dataobject.Boat;
import com.traceroot.dataobject.BoatTrace;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 交叉业务接口
 */
public interface CrossService {

    TreeMap<Integer,String> selectByPassingPipelineSegment(String segmentId,Date startTime,Date endTime,Integer accuracyDegree);

    Map<String,List<BoatTrace>> findBoatNearSegmentDuringTime(String segmentId, Date startTime, Date endTime , Integer accuracyDegree);
}
