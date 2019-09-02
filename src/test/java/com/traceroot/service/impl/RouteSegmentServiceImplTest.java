package com.traceroot.service.impl;

import com.traceroot.dataobject.RouteSegment;
import com.traceroot.dataobject.SeaRoute;
import com.traceroot.dto.RouteSegmentDTO;
import com.traceroot.service.ifs.RouteSegmentService;
import com.traceroot.utils.DoubleLocation;
import com.traceroot.utils.GeographyUtil;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteSegmentServiceImplTest {

    @Autowired
    private RouteSegmentService segmentService;

    private String ROUTEID = "1188454";

    private String SEGMENTID = "4679410";

    @Test
    public void selectByRouteId() {
        List<RouteSegmentDTO> routeSegments = segmentService.selectByRouteId(ROUTEID+"123");
        Assert.assertNotEquals(0,routeSegments.size());
    }

    @Test
    public void selectByRouteIdOrderBySegmentSerialNumberAsc() {
        List<RouteSegmentDTO> segmentList = segmentService.selectByRouteIdOrderBySegmentSerialNumberAsc(ROUTEID);
        Assert.assertNotEquals(0,segmentList.size());
    }

    @Test
    public void insert() {
        RouteSegment routeSegment = new RouteSegment(/*RandomUtil.genUniqueId()*/"204", ROUTEID, RandomUtil.genUniqueLocation(), RandomUtil.genUniqueLocation());
        //RouteSegment result = segmentService.insert(routeSegment);
        //Assert.assertNotNull(result);
    }

    /*@Test
    public void update() {
//        RouteSegment routeSegment = segmentService.selectBySegmentId(SEGMENTID+"123");
        RouteSegment routeSegment = new RouteSegment(SEGMENTID+"123", ROUTEID, RandomUtil.genUniqueLocation(), RandomUtil.genUniqueLocation());
        routeSegment.setStart(RandomUtil.genUniqueLocation());
        //RouteSegment update = segmentService.update(routeSegment);
        Assert.assertNotNull(update);
    }*/

    @Test
    public void deleteBySegmentId() {
        segmentService.deleteBySegmentId("201");
    }

    @Test
    public void deleteByRouteId() {
        segmentService.deleteByRouteId(ROUTEID);
    }


    @Test
    public void selectByRouteIdAndStartNearLocation() {
        DoubleLocation doubleLocation = GeographyUtil.string2doubleLocation("119.797133,37.83008");
        String fuzzyMatchingExpr = GeographyUtil.buildFuzzyMatchingExpr(doubleLocation.getLongitude(),doubleLocation.getLatitude(),-1);
        List<RouteSegmentDTO> routeSegmentDTOS = segmentService.selectByRouteIdAndStartNearLocation("803", fuzzyMatchingExpr);
        Assert.assertNotEquals(0,routeSegmentDTOS.size());
    }
}