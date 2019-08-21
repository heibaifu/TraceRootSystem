package com.traceroot.service.ifs;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.multikeysclass.PipelineSegmentMultiKeys;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.enums.SensorStatusEnum;

import java.util.List;

public interface PipelineSegmentService {

    PipeSegmentDTO selectBySegmentId(String segmentId);

    List<PipeSegmentDTO> selectByPipeId(String pipeId);

    List<PipeSegmentDTO> selectAll();

    List<PipeSegmentDTO> selectBySensorStatus(SensorStatusEnum statusEnum);

    Integer judgeStatus(String segmentId);

    PipelineSegment insert(PipeSegmentDTO pipeSegmentDTO);

    PipelineSegment update(PipeSegmentDTO pipeSegmentDTO);

    List<PipelineSegment> selectByPipeIdAndSegmentSerialNumberAfter(String pipeId, Integer serialNumber);

    void deleteBySegmentId(String segmentId);

    void deleteByPipeId(String pipeId);
}
