package com.traceroot.service.impl;

import com.traceroot.dataobject.BoatTrace;
import com.traceroot.enums.BoatStatusEnum;
import com.traceroot.utils.RandomUtil;
import com.traceroot.utils.String2TimestampUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BoatTraceServiceImplTest {

    @Autowired
    private BoatTraceServiceImpl traceService;

    private String BOATID = "1198795";

    private String TRACEID = "5191791";


    private Timestamp startTime;

    private Timestamp endTime;/* = new Timestamp(System.currentTimeMillis())*/;

    @Test
    public void selectByTraceId() {
        BoatTrace trace = traceService.selectByTraceId(TRACEID);
        Assert.assertEquals(trace.getTraceId(),TRACEID);
    }

    @Test
    public void selectByBoatId() {
        List<BoatTrace> traces = traceService.selectByBoatId(BOATID);
        Assert.assertNotEquals(0,traces.size());
    }

    @Test
    public void selectByRecordTimeBetween() {
        try {
            startTime = String2TimestampUtil.string2Time("2019-07-24 22:15:21");
            endTime = String2TimestampUtil.string2Time("2019-07-24 22:16:05");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        List<BoatTrace> boatTraces = traceService.selectByRecordTimeBetween(startTime, endTime);
        Assert.assertNotEquals(0,boatTraces.size());
    }

    @Test
    public void selectByBoatIdAndRecordTimeBetween() {
        try {
            startTime = String2TimestampUtil.string2Time("2019-07-24 22:15:50");
            endTime = String2TimestampUtil.string2Time("2019-07-24 22:16:34");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        List<BoatTrace> boatTraces = traceService.selectByBoatIdAndRecordTimeBetween(BOATID,startTime, endTime);
        Assert.assertNotEquals(0,boatTraces.size());
    }

    @Test
    public void insert() {
        BoatTrace trace = new BoatTrace(RandomUtil.genUniqueId(),BOATID,RandomUtil.genUniqueLocation(), BoatStatusEnum.NOMAL.getCode().toString());
        BoatTrace result = traceService.insert(trace);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteByBoatId() {
        TRACEID = "8335303";
        traceService.deleteByTraceId(TRACEID);
        BoatTrace trace = traceService.selectByTraceId(TRACEID);
        Assert.assertEquals(trace,null);
    }

    @Test
    public void deleteByTraceId() {
        BOATID = "0995923";
        traceService.deleteByBoatId(BOATID);
        List<BoatTrace> boatTraces = traceService.selectByBoatId(BOATID);
        Assert.assertEquals(0,boatTraces.size());
    }

}