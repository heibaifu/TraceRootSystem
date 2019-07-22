package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.exception.PipeException;
import com.traceroot.dataobject.multikeysclass.PipelineSegmentMultiKeys;
import com.traceroot.enums.ResultEnum;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PipelineSegmentServiceImplTest {

    @Autowired
    PipelineSegmentServiceImpl segmentService;

    @Test
    public void selectBySegmentId() {
        PipelineSegment pipelineSegment=new PipelineSegment();
        pipelineSegment=segmentService.selectBySegmentId("1944616");
        Assert.assertNotNull(pipelineSegment);

    }

    @Test
    public void selectByPipeId() {
        List<PipelineSegment> pipelineSegmentList=new ArrayList<>();
        pipelineSegmentList=segmentService.selectByPipeId("1904289");
        Assert.assertNotNull(pipelineSegmentList);

    }

    @Test
    public void save() {
        String PipeID = "1904289";
        PipelineSegment segment = new PipelineSegment(RandomUtil.genUniqueId(),PipeID,RandomUtil.genUniqueLocation(),RandomUtil.genUniqueLocation());
        PipelineSegment result = segmentService.save(segment);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteById() {
        PipelineSegmentMultiKeys keys = new PipelineSegmentMultiKeys("1513478",5);
        segmentService.deleteById(keys);
    }
}