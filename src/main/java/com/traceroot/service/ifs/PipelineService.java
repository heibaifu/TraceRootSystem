package com.traceroot.service.ifs;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.dto.PipeDTO;

import java.util.List;

public interface PipelineService {

    Pipeline selectByPipeId(String pipeId);

    List<PipeDTO> selectAll();

    Pipeline save(Pipeline pipeline);

    void deleteById(String pipeId);
}
