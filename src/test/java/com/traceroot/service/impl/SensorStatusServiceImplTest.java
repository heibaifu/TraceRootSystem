package com.traceroot.service.impl;

import com.traceroot.dataobject.SensorStatus;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.repository.SensorStatusRepository;
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
public class SensorStatusServiceImplTest {

    @Autowired
    private SensorStatusServiceImpl statusService;

    String SENSORID="5931029";

    @Test
    public void save() throws Exception{
        SensorStatus sensorStatus=new SensorStatus();
        sensorStatus.setSensorId(SENSORID);
        sensorStatus.setStatusId(RandomUtil.genUniqueId());
        sensorStatus.setStatus(SensorStatusEnum.BROKEN.getCode());
        SensorStatus result=statusService.save(sensorStatus);
        Assert.assertNotNull(result);
    }
}