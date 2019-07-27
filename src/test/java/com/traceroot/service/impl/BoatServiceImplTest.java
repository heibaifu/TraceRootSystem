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
import  com.traceroot.dataobject.BoatTrace;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoatServiceImplTest {

    @Autowired
    BoatServiceImpl boatService;

    @Autowired
    BoatTraceServiceImpl boatTraceService;

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
        List<Boat> boatList = boatService.selectByRoute("2472838");
        Assert.assertNotEquals(0,boatList.size());
    }

    @Test
    public void selectByPassingPipelineSegment() {

    }

    @Test
    public void save() {
        Boat boat = new Boat(RandomUtil.genUniqueId(),"002",RandomUtil.genUniqueLocation(), BoatStatusEnum.NOMAL.getCode().toString(),"8211997");
        Boat result = boatService.save(boat);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateByLocation() {
        Boat result=boatService.updateByLocation("2506573","(+116.398834,+39.95369)");
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteById() {
    }

}