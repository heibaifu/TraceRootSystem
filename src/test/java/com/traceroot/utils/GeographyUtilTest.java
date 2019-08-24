package com.traceroot.utils;

import com.traceroot.dto.BoatDTO;
import com.traceroot.dto.BoatTraceDTO;
import com.traceroot.service.ifs.BoatService;
import com.traceroot.service.ifs.BoatTraceService;
import com.traceroot.service.ifs.SensorStatusService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class GeographyUtilTest {

    @Autowired
    private SensorStatusService sensorStatusService;

    @Autowired
    private BoatTraceService boatTraceService;

    @Test
    public void string2doubleLocation() {
        DoubleLocation doubleLocation = GeographyUtil.string2doubleLocation("-133.1564,25.15640");
        log.info(doubleLocation.toString());
    }

    @Test
    public void buildFuzzyMatchingExpr() {
        String result = GeographyUtil.buildFuzzyMatchingExpr(116.35504950497435, -39.96149086928498, -1);
        log.info(result);
    }


    @Test
    public void inferDirection() {
        Double result = GeographyUtil.inferDirection("0,0","-2,-1");
        log.info(result.toString());
    }

    @Test
    public void getDistance() {
        Double result = GeographyUtil.getDistance("116.401969,39.924453","117.21145,39.011836");
        log.info(result.toString());

    }

    @Test
    public void getTimeDiff1() {
        Timestamp t1= (Timestamp) sensorStatusService.selectByStatusId("5455199").getRecordTime();
        Timestamp t2= (Timestamp) sensorStatusService.selectByStatusId("3739974").getRecordTime();
        Double result=TimeUtil.getTimeDiff(t1,t2);
        log.info(String.valueOf(result));
    }

    @Test
    public void getTimeDiff2() {
        Date t1= sensorStatusService.selectByStatusId("8889978").getRecordTime();
        Date t2= sensorStatusService.selectByStatusId("3119667").getRecordTime();
        Double result=TimeUtil.getTimeDiff(t1,t2);
        log.info(String.valueOf(result));
    }

    @Test
    public void getSpeed() {
        BoatTraceDTO boatTraceDTO1=boatTraceService.selectByTraceId("9614790");
        BoatTraceDTO boatTraceDTO2=boatTraceService.selectByTraceId("4781816");
        String result=GeographyUtil.getSpeed(boatTraceDTO1.getRecordLocation(),boatTraceDTO2.getRecordLocation(),boatTraceDTO1.getRecordTime(),boatTraceDTO2.getRecordTime());
        Assert.assertNotEquals(0,result);
    }
}