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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class PipelineSegmentServiceImplTest {

    @Autowired
    PipelineSegmentServiceImpl segmentService;

    static String PipeID = "2044895";

    @Test
    public void selectBySegmentId() {
        PipeSegmentDTO pipelineSegment=new PipeSegmentDTO();
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
        List<PipeSegmentDTO> pipelineSegmentList=new ArrayList<>();
        pipelineSegmentList=segmentService.selectByPipeId(PipeID);
        Assert.assertNotNull(pipelineSegmentList);

    }

    @Test
    public void testifyStatus() {
        int integer = segmentService.testifyStatus("1001");
        Assert.assertEquals(0,integer);
    }

    @Test
    public void insert() {
        PipeSegmentDTO pipeSegmentDTO = new PipeSegmentDTO("2004", "10002", null, "121.267183,38.062677", "121.465672,37.53779", null);
        PipelineSegment result = segmentService.insert(pipeSegmentDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void update() {
        PipeSegmentDTO pipeSegmentDTO = new PipeSegmentDTO("7002954", PipeID, null, RandomUtil.genUniqueLocation(), RandomUtil.genUniqueLocation(), null);
        PipelineSegment result = segmentService.update(pipeSegmentDTO);
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