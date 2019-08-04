package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dto.PipeSegmentDTO;
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

    static String PipeID = "1053733";

    @Test
    public void selectBySegmentId() {
        PipelineSegment pipelineSegment=new PipelineSegment();
        pipelineSegment=segmentService.selectBySegmentId(PipeID);
        Assert.assertNotNull(pipelineSegment);

    }

    @Test
    public void selectAll() {
        List<PipeSegmentDTO> pipeSegmentDTOList = segmentService.selectAll();
        Assert.assertNotNull(pipeSegmentDTOList);
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
    public void deleteByPipeId() {
        segmentService.deleteByPipeId(PipeID);
    }

    @Test
    public void deleteBySegmentId() {

        segmentService.deleteBySegmentId("7304969");
    }

    @Test
    public void selectByWarning() {
        segmentService.selectByWarning();
    }

    @Test
    public void  arrryString(){
        String[] badnodeid=new String[2];
        List<PipeSegmentDTO> warningSegments=segmentService.selectByWarning();
        for (int i=0;i<warningSegments.size();i++){
            badnodeid[i]=warningSegments.get(i).getSegmentId();
        }

        log.info(String.valueOf(badnodeid.length));

    }
}