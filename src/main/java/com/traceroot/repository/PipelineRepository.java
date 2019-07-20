package com.traceroot.repository;

import com.traceroot.dataobject.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PipelineRepository extends JpaRepository<Pipeline,String> {

    Pipeline findByPipeId(String pipeId);

}
