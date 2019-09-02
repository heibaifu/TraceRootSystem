package com.traceroot.repository;

import com.traceroot.dataobject.BoatTrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BoatTraceRepository extends JpaRepository<BoatTrace,String> {

    BoatTrace findByTraceId(String traceId);

    List<BoatTrace> findByBoatIdOrderByTraceSerialNumber(String boatId);

    List<BoatTrace> findByBoatId(String boatId);

    Integer countBoatTraceByBoatId(String boatId);

    List<BoatTrace> findByRecordTimeBetweenOrderByRecordTimeDesc(Date startTime, Date endTime);

    List<BoatTrace> findByRecordTimeBetweenAndRecordLocationIsLikeOrderByRecordTimeDesc(Date startTime, Date endTime,String location);

/*    @Query(value="select boat_id from boat_trace where trace_id in ?1", nativeQuery = true)
    List<String> findBoatId(List<String> traceIdList);*/

    List<BoatTrace> findByRecordLocationIsLikeOrderByRecordTimeDesc(String location);

    List<BoatTrace> findByBoatIdAndRecordTimeBetweenOrderByRecordTimeDesc(String boatId, Date startTime, Date endTime);

}
