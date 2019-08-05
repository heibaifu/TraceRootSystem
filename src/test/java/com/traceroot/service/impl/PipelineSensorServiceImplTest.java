package com.traceroot.service.impl;

import com.traceroot.converter.dao2dto.PipelineSensor2SensorDTOConverter;
import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dto.PipelineSensorDTO;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PipelineSensorServiceImplTest {

    @Autowired
    PipelineSensorServiceImpl service;

    public static String SEGMENTID = "124";

    public static String SENSORID = "1262750";

    public static String PIPEID = "2044895";

    public static String SENSORTYPE = "2926155";

    public static SensorStatusEnum STATUS = SensorStatusEnum.NORMAL;

    //private static String  ;

    @Test
    public void selectBySegmentId() {
        List<PipelineSensorDTO> result = service.selectBySegmentId(SEGMENTID);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectBySensorId() {
        PipelineSensorDTO result=service.selectBySensorId(SENSORID);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectByPipeId() {
        List<PipelineSensorDTO> result=service.selectByPipeId(PIPEID);
        Assert.assertNotNull(result);
    }

    @Test
    public void save() throws Exception{

        PipelineSensor sensor = new PipelineSensor(RandomUtil.genUniqueId(),SEGMENTID,PIPEID,SENSORTYPE,RandomUtil.genUniqueLocation(),SensorStatusEnum.ABNORMAL.getCode());
        PipelineSensorDTO pipelineSensorDTO= PipelineSensor2SensorDTOConverter.convert(sensor);
        PipelineSensorDTO result = service.save(pipelineSensorDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteBySensorId() throws Exception{
            service.deleteBySensorId(SENSORID);
    }

    @Test
    public void updateByStatus() throws Exception{

        PipelineSensorDTO pipelineSensor=service.updateByStatus("5931029",SensorStatusEnum.ABNORMAL.getCode());
        Assert.assertNotNull(pipelineSensor);
    }


}