package com.traceroot.service.ifs;

import com.traceroot.dataobject.BoatTrace;
import com.traceroot.dto.BoatTraceDTO;

import java.util.Date;
import java.util.List;

public interface BoatTraceService {

    BoatTraceDTO selectByTraceId (String traceId);

    List<BoatTraceDTO> selectByBoatId (String boatId);

    List<BoatTraceDTO> selectByRecordTimeBetween(Date startTime, Date endTime);

    List<BoatTraceDTO> selectByBoatIdAndRecordTimeBetween(String traceId, Date startTime, Date endTime);

    List<BoatTrace> selectByRecordTimeBetweenAndRecordLocationIsLikeOrderByRecordTimeDesc(Date startTime, Date endTime,String location);

    List<BoatTraceDTO> selectByRecordTimeAndRecordLocation(Date startTime, Date endTime,String location);

    List<BoatTraceDTO> selectByLocationIsLikeOrderByRecordTimeDesc(String location);

    BoatTrace insert (BoatTraceDTO boatTrace);

    void deleteByBoatId (String boatId);

    void deleteByTraceId (String traceId);
}
