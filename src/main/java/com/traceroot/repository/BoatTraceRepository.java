package com.traceroot.repository;

import com.traceroot.dataobject.BoatTrace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BoatTraceRepository extends JpaRepository<BoatTrace,String> {

    BoatTrace findByTraceId(String traceId);

    List<BoatTrace> findByBoatIdOrderByTraceSerialNumber(String boatId);

    List<BoatTrace> findByBoatId(String boatId);

    Integer countBoatTraceByBoatId(String boatId);

    List<BoatTrace> findByRecordTimeBetweenOrderByRecordTimeDesc(Date startTime, Date endTime);

    List<BoatTrace> findByBoatIdAndRecordTimeBetweenOrderByRecordTimeDesc(String boatId, Date startTime, Date endTime);

}
