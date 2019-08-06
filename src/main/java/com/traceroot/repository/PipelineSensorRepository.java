package com.traceroot.repository;

import com.traceroot.dataobject.PipelineSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PipelineSensorRepository extends JpaRepository<PipelineSensor,String> {

    PipelineSensor findBySensorId(String sensorId);

    PipelineSensor findByLocation(String location);

    List<PipelineSensor> findByPipeId(String pipeId);

    List<PipelineSensor> findBySegmentId(String segmentId);

    List<PipelineSensor> findByPresentStatus(String statusEnum);

}
