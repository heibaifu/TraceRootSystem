package com.traceroot.service.impl;

import com.traceroot.dataobject.SeaRoute;
import com.traceroot.enums.SeaRouteStatusEnum;
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
public class SeaRouteServiceImplTest {

    @Autowired
    private SeaRouteServiceImpl seaRouteService;

    @Test
    public void insert() {
        SeaRoute seaRoute=new SeaRoute();
        seaRoute.setRouteId(RandomUtil.genUniqueId());
        seaRoute.setStatus(SeaRouteStatusEnum.CANCLE.getCode());
        seaRoute.setSource(RandomUtil.genUniqueLocation());
        seaRoute.setDestination(RandomUtil.genUniqueLocation());
        seaRouteService.insert(seaRoute);
        Assert.assertNotNull(seaRoute);
    }

    @Test
    public void update() {
        SeaRoute seaRoute=seaRouteService.selectByRouteId("7082610");
        seaRoute.setStatus(SeaRouteStatusEnum.AVAILABLE.getCode());
        seaRouteService.update(seaRoute);
        Assert.assertNotNull(seaRoute);

    }

    @Test
    public void selectByRouteId() {
        SeaRoute result=seaRouteService.selectByRouteId("7082610");
        Assert.assertNotNull(result);
    }

    @Test
    public void selectByStatus() {
        List<SeaRoute> seaRouteList=seaRouteService.selectByStatus(SeaRouteStatusEnum.CANCLE.getCode());
        Assert.assertNotEquals(0,seaRouteList.size());
    }

    @Test
    public void deleteByRouteId() {

        seaRouteService.deleteByRouteId("7082610");
    }

    @Test
    public void deleteByStatus() {

        seaRouteService.deleteByStatus(SeaRouteStatusEnum.CANCLE.getCode());
    }
}