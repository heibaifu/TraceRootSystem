package com.traceroot.utils;

import com.traceroot.dataobject.BoatTrace;
import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.service.impl.BoatTraceServiceImpl;
import com.traceroot.service.impl.PipelineSegmentServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MathUtilTest {

    @Autowired
    private PipelineSegmentServiceImpl segmentService;

    @Autowired
    private BoatTraceServiceImpl traceService;

    @Test
    public void intersection() {

        PipelineSegment testSegment = segmentService.selectBySegmentId("1326574");
        DoubleLocation startLine1 = LocationUtil.string2doubleLocation(testSegment.getStart());
        DoubleLocation endLine1 = LocationUtil.string2doubleLocation(testSegment.getEnd());

        List<BoatTrace> boatTraceList = traceService.selectByBoatId("0673058");

        BoatTrace testTraceStart = boatTraceList.get(0);
        DoubleLocation startLine2 = LocationUtil.string2doubleLocation(testTraceStart.getRecordLocation());
        BoatTrace testTraceEnd  = boatTraceList.get(1);
        DoubleLocation endLine2 = LocationUtil.string2doubleLocation(testTraceEnd.getRecordLocation());

        boolean result = MathUtil.intersection(startLine1, endLine1, startLine2, endLine2);
        Assert.assertEquals(false,result);

    }
}