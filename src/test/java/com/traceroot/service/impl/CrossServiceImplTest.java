package com.traceroot.service.impl;

import com.traceroot.dataobject.Boat;
import com.traceroot.dataobject.BoatTrace;
import com.traceroot.utils.String2TimestampUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CrossServiceImplTest {

    @Autowired
    CrossServiceImpl service;

    private Date startTime;

    private Date endTime;

    @Test
    public void findBoatNearSegmentDuringTime() {
        try {
            startTime = String2TimestampUtil.string2Time("2019-07-27 17:10:08");
            endTime = String2TimestampUtil.string2Time("2019-07-28 20:50:56");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        Map<String, List<BoatTrace>> map = service.findBoatNearSegmentDuringTime("123", startTime, endTime, 1);
        Assert.assertNotEquals(0,map.size());
    }

    @Test
    public void selectByPassingPipelineSegment() {
        try {
            startTime = String2TimestampUtil.string2Time("2019-07-27 19:17:18");
            endTime = String2TimestampUtil.string2Time("2019-07-28 18:49:25");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        TreeMap<Integer, List<String>> map = service.selectByPassingPipelineSegment("123", startTime, endTime, 1);
        Assert.assertNotEquals(0,map.size());
    }
}