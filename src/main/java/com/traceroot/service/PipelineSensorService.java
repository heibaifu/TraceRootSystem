package com.traceroot.service;

import com.traceroot.dataobject.PipelineSensor;

import java.util.List;

/**
 * 管道传感器service
 */
public interface PipelineSensorService {

    List<PipelineSensor> selectBySegmentId(String segmentId);

    PipelineSensor selectBySensorId(String sensorId);

    List<PipelineSensor> selectByPipeId(String pipeId);

    PipelineSensor save(PipelineSensor sensor);

    void deleteBySensorId(String sensorId);
}
