package com.traceroot.service;

import com.traceroot.dataobject.BoatTrace;

import java.util.Date;
import java.util.List;

public interface BoatTraceService {

    BoatTrace selectByTraceId (String traceId);

    List<BoatTrace> selectByBoatId (String boatId);

    List<BoatTrace> selectByRecordTimeBetween(Date startTime, Date endTime);

    List<BoatTrace> selectByBoatIdAndRecordTimeBetween(String traceId, Date startTime, Date endTime);

    BoatTrace insert (BoatTrace boatTrace);

    void deleteByBoatId (String boatId);

    void deleteByTraceId (String traceId);
}
