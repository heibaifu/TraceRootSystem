package com.traceroot.service.impl;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PipelineServiceImplTest {

    @Autowired
    PipelineServiceImpl pipelineService ;

    @Test
    public void selectByPipeId() throws Exception{
        Pipeline result = pipelineService.selectByPipeId("3691622");
        log.info(result.toString());
    }

    @Test
    public void selectAll() throws Exception{
        List<Pipeline> pipelines = pipelineService.selectAll();
        log.info(pipelines.toString());
    }

    @Test
    public void save() throws Exception{
        for (int i = 0; i < 10; i++) {
            Pipeline pipeline = new Pipeline(RandomUtil.genUniqueId(),RandomUtil.genUniqueLocation(),RandomUtil.genUniqueLocation());
            Pipeline result = pipelineService.save(pipeline);
            Assert.assertNotNull(result);
        }
    }

    @Test
    public void update() throws Exception{
        Pipeline pipeline = pipelineService.selectByPipeId("1904289");
        pipeline.setDestination(RandomUtil.genUniqueLocation());
        Pipeline result = pipelineService.save(pipeline);
        Assert.assertNotNull(result);
    }
}