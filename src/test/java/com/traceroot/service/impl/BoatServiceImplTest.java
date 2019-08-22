package com.traceroot.service.impl;

import com.traceroot.dataobject.Boat;
import com.traceroot.dto.BoatDTO;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//websocket是需要依赖tomcat等容器的启动。所以在测试过程中我们要真正的启动一个tomcat作为容器。
public class BoatServiceImplTest {

    @Autowired
    private BoatServiceImpl boatService;

    @Autowired
    private BoatTraceServiceImpl boatTraceService;

    public static String BOATID = "0001";

    String RUTEID="1234";

    @Test
    public void selectByBoatId() {
        BoatDTO boatDTO = boatService.selectByBoatId(BOATID);
        Assert.assertEquals(BOATID,boatDTO.getBoatId());
    }

    /*@Test
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
        //Boat boat = new Boat(RandomUtil.genUniqueId(),"003",RandomUtil.genUniqueLocation(), BoatStatusEnum.NOMAL.getCode().toString(),"6479668");
        Boat boat = new Boat(RandomUtil.genUniqueId(),"003","(+116.36565,+39.967115)", BoatStatusEnum.NOMAL.getCode().toString(),"6479668");
        Boat result = boatService.save(boat);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateByLocation() {
        Boat result=boatService.updateByLocation("5562266","(+116.366746,+39.967792)");
        Assert.assertNotNull(result);
    }*/

    @Test
    public void deleteById() {
    }


    @Test
    public void directionCalculate() {
        Double directionCalculate = boatService.directionCalculate(BOATID + "1", "120.972692,38.59829");
        Assert.assertNotNull(directionCalculate);
    }
}