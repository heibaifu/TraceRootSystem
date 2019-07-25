package com.traceroot.service.impl;

import com.traceroot.dataobject.Boat;
import com.traceroot.enums.BoatStatusEnum;
import com.traceroot.utils.RandomUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoatServiceImplTest {

    @Autowired
    BoatServiceImpl boatService;

    public static String BOATID = "6126226";

    String RUTEID="1234";

    @Test
    public void selectByBoatId() {
        Boat boat = boatService.selectByBoatId(BOATID);
        Assert.assertEquals(BOATID,boat.getBoatId());
    }

    @Test
    public void selectByStatus() {
        List<Boat> boatList = boatService.selectByStatus("151");
        Assert.assertNotNull(boatList);
    }

    @Test
    public void selectAllBoat() {
        List<Boat> boatList = boatService.selectAllBoat();
        Assert.assertNotNull(boatList);
    }

    @Test
    public void selectByTypeIn() {
        List<String> typeList = new ArrayList<>() ;
        typeList.add("001");
        typeList.add("003");
        List<Boat> boatList = boatService.selectByTypeIn(typeList);
        Assert.assertNotNull(boatList);
    }


    @Test
    public void selectByRoute() {
        //todo 根据航线挑选船只测试未做
        //boatService.selectByRoute();


    }

    @Test
    public void selectByPassingPipelineSegment() {

    }

    @Test
    public void insert() {
        for (int i = 0; i < 1; i++) {
            Boat boat = new Boat(RandomUtil.genUniqueId(),"003",RandomUtil.genUniqueLocation(), BoatStatusEnum.NOMAL.getCode().toString());
            Boat result = boatService.save(boat);
            Assert.assertNotNull(result);
        }
    }

    @Test
    public void update() {
        Boat boat = boatService.selectByBoatId(BOATID);
        boat.setType("大渔船");
        Boat result = boatService.save(boat);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteById() {
    }

}