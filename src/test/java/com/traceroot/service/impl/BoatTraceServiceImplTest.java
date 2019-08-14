package com.traceroot.service.impl;

import com.traceroot.dataobject.BoatTrace;
import com.traceroot.dto.BoatTraceDTO;
import com.traceroot.enums.BoatStatusEnum;
import com.traceroot.utils.RandomUtil;
import com.traceroot.utils.TimeUtil;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BoatTraceServiceImplTest {

    @Autowired
    private BoatTraceServiceImpl traceService;

    private String BOATID = "0641976";

    private String TRACEID = "2004773";


    private Timestamp startTime;

    private Timestamp endTime;/* = new Timestamp(System.currentTimeMillis())*/;

    @Test
    public void selectByTraceId() {
        BoatTraceDTO boatTraceDTO = traceService.selectByTraceId(TRACEID);
        Assert.assertEquals(boatTraceDTO.getTraceId(),TRACEID);
    }

    @Test
    public void selectByBoatId() {
        List<BoatTraceDTO> traceDTOList = traceService.selectByBoatId(BOATID);
        Assert.assertNotEquals(0,traceDTOList.size());
    }

    @Test
    public void selectByRecordTimeBetween() {
        try {
            startTime = TimeUtil.string2Timestamp("2019-07-24 22:15:21");
            endTime = TimeUtil.string2Timestamp("2019-07-24 22:16:05");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        List<BoatTraceDTO> traceDTOList = traceService.selectByRecordTimeBetween(startTime, endTime);
        Assert.assertNotEquals(0,traceDTOList.size());
    }

    @Test
    public void selectByBoatIdAndRecordTimeBetween() {
        try {
            startTime = TimeUtil.string2Timestamp("2019-07-24 22:15:50");
            endTime = TimeUtil.string2Timestamp("2019-07-24 22:16:34");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        List<BoatTraceDTO> boatTraceDTOS = traceService.selectByBoatIdAndRecordTimeBetween(BOATID, startTime, endTime);
        Assert.assertNotEquals(0,boatTraceDTOS.size());
    }

    @Test
    public void insert() {
        BoatTraceDTO boatTraceDTO = new BoatTraceDTO(RandomUtil.genUniqueId(), BOATID, RandomUtil.genUniqueLocation(), BoatStatusEnum.NOMAL.getCode().toString());
        BoatTrace result = traceService.insert(boatTraceDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteByBoatId() {
        TRACEID = "8335303";
        traceService.deleteByTraceId(TRACEID);
        BoatTraceDTO traceDTO = traceService.selectByTraceId(TRACEID);
        Assert.assertEquals(traceDTO,null);
    }

    @Test
    public void deleteByTraceId() {
        BOATID = "0995923";
        traceService.deleteByBoatId(BOATID);
        List<BoatTraceDTO> boatTraceDTOS = traceService.selectByBoatId(BOATID);
        Assert.assertEquals(0,boatTraceDTOS.size());
    }


    @Test
    public void selectByRecordTimeBetweenAndRecordLocationIsLikeOrderByRecordTimeDesc() {
        try {
            startTime = TimeUtil.string2Timestamp("2019-07-25 17:10:08");
            endTime = TimeUtil.string2Timestamp("2019-07-29 21:31:56");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        List<BoatTraceDTO> boatTraceDTOS = traceService
                .selectByRecordTimeAndRecordLocation(startTime, endTime, "116.3%,39.9%");
        /*List<BoatTrace> boatTraceList = traceService.selectByLocationIsLikeOrderByRecordTimeDesc("116.3%,39.9%");*/
        Assert.assertNotEquals(0,boatTraceDTOS.size());
    }
}