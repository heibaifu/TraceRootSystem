package com.traceroot.service.ifs;

import com.traceroot.dataobject.Pipeline;

import java.util.List;

public interface PipelineService {

    Pipeline selectByPipeId(String pipeId);

    List<Pipeline> selectAll();

    Pipeline save(Pipeline pipeline);

    void deleteById(String pipeId);
}
