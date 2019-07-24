package com.traceroot.service.impl;

import com.traceroot.dataobject.SensorType;
import com.traceroot.service.SensorTypeService;
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
public class SensorTypeServiceImplTest {

    @Autowired
    private SensorTypeService typeService;

    String TYPENAME="猪猪传感器";
    String TYPEID="1142987";


    @Test
    public void save() throws Exception{
        SensorType sensorType=new SensorType();
        sensorType.setTypeName("质量传感器");
        sensorType.setTypeId(RandomUtil.genUniqueId());
        SensorType result=typeService.save(sensorType);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectByTypeId()  throws Exception{
        SensorType sensorType=typeService.selectByTypeId(TYPEID);
        Assert.assertNotNull(sensorType);
    }

    @Test
    public void selectByTypeName()  throws Exception{
        SensorType sensorType=typeService.selectByTypeName(TYPENAME);
        Assert.assertNotNull(sensorType);
    }

    @Test
    public void updateByTypeId()  throws Exception{
        SensorType sensorType=typeService.updateByTypeId(TYPEID,"猪屁传感器");
        Assert.assertNotNull(sensorType);
    }

    @Test
    public void deleteByTypeId()  throws Exception{
        typeService.deleteByTypeId(TYPEID);
    }
}