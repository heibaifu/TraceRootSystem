package com.traceroot.service.impl;

import com.traceroot.dataobject.BoatType;
import com.traceroot.service.ifs.BoatTypeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class BoatTypeServiceImplTest {

    @Autowired
    private BoatTypeService boatTypeService;

    String TYPEID="004";
    String TYPENAME="屁屁船";

    @Test
    public void insert() throws Exception{
        BoatType boatType=new BoatType();
        boatType.setTypeId("004");
        boatType.setTypeName("货轮");
        boatTypeService.insert(boatType);
        Assert.assertNotNull(boatType);
    }

    @Test
    public void update() throws Exception{

        BoatType result=boatTypeService.updateByTypeId(TYPEID,"屁屁船");
        Assert.assertNotNull(result);
    }

    @Test
    public void selectByTypeId() throws Exception{
        BoatType result=boatTypeService.selectByTypeId(TYPEID);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectByTypeName() throws Exception{
        BoatType result=boatTypeService.selectByTypeName(TYPENAME);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteByTypeId() throws Exception{
        boatTypeService.deleteByTypeId(TYPEID);
    }
}