package com.traceroot.repository;

import com.traceroot.dataobject.SensorStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface SensorStatusRepository extends JpaRepository<SensorStatus,String> {

    Page<SensorStatus> findBySensorIdOrderByRecordTimeDesc(String sensorId);

    Page<SensorStatus> findByRecordTimeGreaterThanEqual(Data recordTime);   //时间大于等于...

    SensorStatus findBySensorId (String statusId);    //按状态id查找


}
