package com.traceroot.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class GeographyUtilTest {

    @Test
    public void string2doubleLocation() {
        DoubleLocation doubleLocation = GeographyUtil.string2doubleLocation("-133.1564,25.15640");
        log.info(doubleLocation.toString());
    }

    @Test
    public void buildFuzzyMatchingExpr() {
        String result = GeographyUtil.buildFuzzyMatchingExpr(116.35504950497435, -39.96149086928498, -1);
        log.info(result);
    }


    @Test
    public void inferDirection() {
        Double result = GeographyUtil.inferDirection("0,0","-2,-1");
        log.info(result.toString());
    }
}