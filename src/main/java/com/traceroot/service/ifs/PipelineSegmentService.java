package com.traceroot.service.ifs;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.service.FaultHistory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public interface PipelineSegmentService {

    PipeSegmentDTO selectBySegmentId(String segmentId);

    List<PipeSegmentDTO> selectByPipeId(String pipeId);

    List<PipeSegmentDTO> selectAll();

    List<PipeSegmentDTO> selectBySensorStatus(SensorStatusEnum statusEnum);

    Integer judgeStatus(String segmentId);

    PipelineSegment insert(PipeSegmentDTO pipeSegmentDTO);

    PipelineSegment update(PipeSegmentDTO pipeSegmentDTO);

    List<PipelineSegment> selectByPipeIdAndSegmentSerialNumberAfter(String pipeId, Integer serialNumber);

    TreeMap<Date,List<FaultHistory>> builtFaultHistory(String segmentId);

    void deleteBySegmentId(String segmentId);

    void deleteByPipeId(String pipeId);
}
