package com.traceroot.service.impl;

import com.traceroot.dataobject.RouteSegment;
import com.traceroot.dataobject.SeaRoute;
import com.traceroot.utils.RandomUtil;
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
public class RouteSegmentServiceImplTest {

    @Autowired
    private RouteSegmentServiceImpl segmentService;

    private String ROUTEID = "8406013";

    private String SEGMENTID = "4679410";

    @Test
    public void selectByRouteId() {
        List<RouteSegment> routeSegments = segmentService.selectByRouteId(ROUTEID+"123");
        Assert.assertNotEquals(0,routeSegments.size());
    }

    @Test
    public void selectByRouteIdOrderBySegmentSerialNumberAsc() {
        List<RouteSegment> segmentList = segmentService.selectByRouteIdOrderBySegmentSerialNumberAsc(ROUTEID);
        Assert.assertNotEquals(0,segmentList.size());
    }

    @Test
    public void insert() {
        RouteSegment routeSegment = new RouteSegment(RandomUtil.genUniqueId(), ROUTEID, RandomUtil.genUniqueLocation(), RandomUtil.genUniqueLocation());
        RouteSegment result = segmentService.insert(routeSegment);
        Assert.assertNotNull(result);
    }

    @Test
    public void update() {
//        RouteSegment routeSegment = segmentService.selectBySegmentId(SEGMENTID+"123");
        RouteSegment routeSegment = new RouteSegment(SEGMENTID+"123", ROUTEID, RandomUtil.genUniqueLocation(), RandomUtil.genUniqueLocation());
        routeSegment.setStart(RandomUtil.genUniqueLocation());
        RouteSegment update = segmentService.update(routeSegment);
        Assert.assertNotNull(update);
    }

    @Test
    public void deleteBySegmentId() {
        segmentService.deleteBySegmentId(SEGMENTID);
    }

    @Test
    public void deleteByRouteId() {
        segmentService.deleteByRouteId(ROUTEID);
    }
}