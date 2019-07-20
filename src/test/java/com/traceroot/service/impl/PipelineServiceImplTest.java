package com.traceroot.service.impl;

import com.traceroot.dataobject.Pipeline;
import com.traceroot.utils.RandomUtil;
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
public class PipelineServiceImplTest {

    @Autowired
    PipelineServiceImpl pipelineService ;

    @Test
    public void selectByPipeId() {

    }

    @Test
    public void selectAll() {
        List<Pipeline> pipelines = pipelineService.selectAll();
    }

    @Test
    public void save() {
        Pipeline pipeline = new Pipeline(RandomUtil.genUniqueId(),RandomUtil.genUniqueLocation(),RandomUtil.genUniqueLocation());
        Pipeline result = pipelineService.save(pipeline);
        Assert.assertNotNull(result);
    }
}