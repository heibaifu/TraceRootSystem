package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PipelineSegmentServiceImplTest {

    @Autowired
    PipelineSegmentServiceImpl segmentService;

    @Test
    public void selectBySegmentId() {
    }

    @Test
    public void selectBypipeId() {
    }

    @Test
    public void save() {
        String PipeID = "1904289";
        PipelineSegment segment = new PipelineSegment(RandomUtil.genUniqueId(),PipeID,RandomUtil.genUniqueLocation(),RandomUtil.genUniqueLocation());
        PipelineSegment result = segmentService.save(segment);
        Assert.assertNotNull(result);
    }

    @Test
    public void delete() {
    }
}