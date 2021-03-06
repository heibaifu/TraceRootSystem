package com.traceroot.service.impl;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.dto.PipeDTO;
import com.traceroot.exception.PipeException;
import com.traceroot.service.ifs.PipelineService;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PipelineServiceImplTest {

    @Autowired
    private PipelineService pipelineService ;

    static String PIPEID = "4342832";

    @Test
    public void selectByPipeId() throws Exception{
        Pipeline result = pipelineService.selectByPipeId(PIPEID);
        log.info(result.toString());
    }

    @Test
    public void selectAll() throws Exception{
        List<PipeDTO> pipeDTOList = pipelineService.selectAll();
        log.info(pipeDTOList.toString());
    }

    @Test
    public void save() throws Exception{
        PipeDTO pipeDTO = new PipeDTO(RandomUtil.genUniqueId(),RandomUtil.genUniqueLocation(),RandomUtil.genUniqueLocation()/*, TimeUtil.string2Timestamp("2019-08-04 11:11:11")*/);
        Pipeline result = pipelineService.save(pipeDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void update() throws Exception{
        /*Pipeline pipeline = pipelineService.selectByPipeId(PIPEID);
        pipeline.setDestination(RandomUtil.genUniqueLocation());
        Pipeline result = pipelineService.save(pipeline);
        Assert.assertNotNull(result);*/
    }

    @Test
    public void deleteById() throws Exception{
        try {
            pipelineService.deleteById(PIPEID);
        } catch (PipeException e) {
            log.error(e.getMessage());
        }
    }

}