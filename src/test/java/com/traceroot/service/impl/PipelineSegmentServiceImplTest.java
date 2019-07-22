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

    static String PipeID = "1904289";

    @Test
    public void selectBySegmentId() {
        PipelineSegment pipelineSegment=new PipelineSegment();
        pipelineSegment=segmentService.selectBySegmentId(PipeID);
        Assert.assertNotNull(pipelineSegment);

    }

    @Test
    public void selectByPipeId() {
        List<PipelineSegment> pipelineSegmentList=new ArrayList<>();
        pipelineSegmentList=segmentService.selectByPipeId(PipeID);
        Assert.assertNotNull(pipelineSegmentList);

    }

    @Test
    public void insert() {
        PipelineSegment segment = new PipelineSegment(RandomUtil.genUniqueId(),PipeID,RandomUtil.genUniqueLocation(),RandomUtil.genUniqueLocation());
        PipelineSegment result = segmentService.insert(segment);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteById() {
        segmentService.deleteById(PipeID);
    }
}