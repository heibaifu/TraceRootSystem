package com.traceroot.service.impl;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.dto.PipeDTO;
import com.traceroot.exception.PipeException;
import com.traceroot.enums.ResultEnum;
import com.traceroot.repository.PipelineRepository;
import com.traceroot.service.ifs.PipelineService;
import com.traceroot.converter.Pipeline2PipeDTOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class PipelineServiceImpl implements PipelineService {

    @Autowired
    private PipelineRepository repository;

    @Override
    public Pipeline selectByPipeId(String pipeId) {
        return  repository.findByPipeId(pipeId);
    }

    /**
     * 查找所有管道信息
     * @return
     */
    @Override
    public List<PipeDTO> selectAll() {
        List<Pipeline> pipelineList = repository.findAll();
        List<PipeDTO> pipeDTOList = Pipeline2PipeDTOConverter.convert(pipelineList);
        return pipeDTOList;
    }

    @Override
    @Transactional
    public Pipeline save(PipeDTO pipeDTO) {
        Pipeline pipeline = new Pipeline();
        BeanUtils.copyProperties(pipeDTO,pipeline);
        return repository.save(pipeline);
    }

    @Override
    public void deleteById(String pipeId) {
        Pipeline pipeline = repository.findByPipeId(pipeId);
        if (pipeline==null){
            throw new PipeException(ResultEnum.PIPE_NOT_EXIST);
        }
        repository.delete(pipeline);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
