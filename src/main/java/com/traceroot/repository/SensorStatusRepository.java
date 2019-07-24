package com.traceroot.repository;

import com.traceroot.dataobject.SensorStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface SensorStatusRepository extends JpaRepository<SensorStatus,String> {

    List<SensorStatus> findBySensorIdOrderByRecordTimeDesc(String sensorId);

    //List<SensorStatus> findByRecordTimeGreaterThanEqual(Data recordTime);   //时间大于等于...

    SensorStatus findByStatusId (String statusId);    //按状态id查找

    //todo
    List<SensorStatus>findByRecordTimeBetweenOrderByRecordTime(Date statTime, Date endTime);
}
