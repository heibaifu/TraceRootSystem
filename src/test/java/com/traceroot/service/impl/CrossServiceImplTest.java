package com.traceroot.service.impl;

import com.traceroot.dataobject.BoatTrace;
import com.traceroot.service.ifs.CrossService;
import com.traceroot.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CrossServiceImplTest {

    @Autowired
    private CrossService service;

    private Date startTime;

    private Date endTime;

    @Test
    public void findBoatNearSegmentDuringTime() {
        try {
            startTime = TimeUtil.string2Timestamp("2019-07-27 17:10:08");
            endTime = TimeUtil.string2Timestamp("2019-08-18 16:46:04");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        Map<String, List<BoatTrace>> map = service.findBoatNearSegmentDuringTime("2003", startTime, endTime, 0);
        Assert.assertNotEquals(0,map.size());
    }

    @Test
    public void selectByPassingPipelineSegment() {
        try {
            startTime = TimeUtil.string2Timestamp("2019-07-27 19:17:18");
            endTime = TimeUtil.string2Timestamp("2019-08-18 16:46:04");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        NavigableMap<Integer, List<String>> map = service.selectByPassingPipelineSegment("2003", startTime, endTime, 0);
        Assert.assertNotEquals(0,map.size());
    }
}