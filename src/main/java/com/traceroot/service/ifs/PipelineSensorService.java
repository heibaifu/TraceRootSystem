package com.traceroot.service.ifs;

import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.enums.SensorStatusEnum;

import java.util.List;

/**
 * 管道传感器service
 */
public interface PipelineSensorService {

    List<PipelineSensorDTO> selectBySegmentId(String segmentId);

    PipelineSensorDTO selectBySensorId(String sensorId);

    List<PipelineSensorDTO> selectByPipeId(String pipeId);

    PipelineSensorDTO save(PipelineSensorDTO pipelineSensorDTO);

    //管道段传感器状态更新
    PipelineSensorDTO updateByStatus(String sensorId, String updateStatus);

    List<PipelineSensorDTO> selectByPresentStatus(SensorStatusEnum statusEnum);

    void deleteBySensorId(String sensorId);
}
