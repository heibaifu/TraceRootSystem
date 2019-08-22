package com.traceroot.utils;

import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dto.BoatTraceDTO;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.service.impl.BoatTraceServiceImpl;
import com.traceroot.service.impl.PipelineSegmentServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MathUtilTest {

    @Autowired
    private PipelineSegmentServiceImpl segmentService;

    @Autowired
    private BoatTraceServiceImpl traceService;

    @Test
    public void intersection() {

        PipeSegmentDTO pipeSegmentDTO = segmentService.selectBySegmentId("1326574");
        PipelineSegment testSegment = new PipelineSegment();
        BeanUtils.copyProperties(pipeSegmentDTO,testSegment);
        DoubleLocation startLine1 = GeographyUtil.string2doubleLocation(testSegment.getStart());
        DoubleLocation endLine1 = GeographyUtil.string2doubleLocation(testSegment.getEnd());

        List<BoatTraceDTO> boatTraceDTOS = traceService.selectByBoatId("0673058");

        BoatTraceDTO testTraceStart = boatTraceDTOS.get(0);
        DoubleLocation startLine2 = GeographyUtil.string2doubleLocation(testTraceStart.getRecordLocation());
        BoatTraceDTO testTraceEnd  = boatTraceDTOS.get(1);
        DoubleLocation endLine2 = GeographyUtil.string2doubleLocation(testTraceEnd.getRecordLocation());

        boolean result = MathUtil.intersection(startLine1, endLine1, startLine2, endLine2);
        Assert.assertEquals(false,result);

    }
}