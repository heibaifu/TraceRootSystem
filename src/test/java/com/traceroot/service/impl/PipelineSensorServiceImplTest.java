package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dataobject.exception.PipeException;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PipelineSensorServiceImplTest {

    @Autowired
    PipelineSensorServiceImpl service;

    public static String SEGMENTID = "1326574";

    public static String SENSORID = "1904289";

    public static String PIPEID = "1904289";

    public static SensorStatusEnum TYPE = SensorStatusEnum.NOMAL;

    //private static String  ;

    @Test
    public void selectBySegmentId() {
        List<PipelineSensor> result = service.selectBySegmentId(SEGMENTID);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectBySensorId() {
        PipelineSensor result=service.selectBySensorId(SENSORID);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectByPipeId() {
        List<PipelineSensor> result=service.selectByPipeId(PIPEID);
        Assert.assertNotNull(result);
    }

    @Test
    public void save() throws Exception{

        PipelineSensor sensor = new PipelineSensor(RandomUtil.genUniqueId(),SEGMENTID,PIPEID,"坏猪屁传感器",RandomUtil.genUniqueLocation(),TYPE.getMessage());
        PipelineSensor result = service.save(sensor);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteBySensorId() throws Exception{
        try {
            service.deleteBySensorId(SENSORID);
        } catch (PipeException e) {
            log.error(e.getMessage());
        }
    }
}