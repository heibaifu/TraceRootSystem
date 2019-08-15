package com.traceroot.service.impl;

import com.traceroot.dataobject.SensorType;
import com.traceroot.dto.SensorTypeDTO;
import com.traceroot.service.ifs.SensorTypeService;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        SensorTypeDTO sensorType=new SensorTypeDTO();
        sensorType.setTypeName("传感器");
        sensorType.setTypeId(RandomUtil.genUniqueId());
        SensorTypeDTO result=typeService.save(sensorType);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectByTypeId()  throws Exception{
        SensorTypeDTO sensorType=typeService.selectByTypeId(TYPEID);
        Assert.assertNotNull(sensorType);
    }

    @Test
    public void selectByTypeName()  throws Exception{
        SensorTypeDTO sensorType=typeService.selectByTypeName(TYPENAME);
        Assert.assertNotNull(sensorType);
    }

    /*@Test
    public void updateByTypeId()  throws Exception{
        SensorType sensorType=typeService.updateByTypeId(TYPEID,"猪屁传感器");
        Assert.assertNotNull(sensorType);
    }*/

    @Test
    public void deleteByTypeId()  throws Exception{
        typeService.deleteByTypeId(TYPEID);
    }
}