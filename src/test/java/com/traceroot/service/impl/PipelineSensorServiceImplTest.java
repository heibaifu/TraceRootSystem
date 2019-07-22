package com.traceroot.service.impl;

import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PipelineSensorServiceImplTest {

    @Autowired
    PipelineSensorServiceImpl service;

    private static String SEGMENTID = "1904289";

    private static String SENSORID = "1904289";

    private static String PIPEID = "1904289";

    @Test
    public void selectBySegmentId() {
    }

    @Test
    public void selectBySensorId() {
    }

    @Test
    public void selectByPipeId() {
    }

    @Test
    public void save() {
        PipelineSensor sensor = new PipelineSensor(/*RandomUtil.genUniqueId(),*/);
        PipelineSensor result = service.save(sensor);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteBySensorId() {
    }
}