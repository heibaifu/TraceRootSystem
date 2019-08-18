package com.traceroot.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LocationUtilTest {

    @Test
    public void string2doubleLocation() {
        DoubleLocation doubleLocation = LocationUtil.string2doubleLocation("-133.1564,25.15640");
        log.info(doubleLocation.toString());
    }

    @Test
    public void buildFuzzyMatchingExpr() {
        String result = LocationUtil.buildFuzzyMatchingExpr(116.35504950497435, -39.96149086928498, -1);
        log.info(result);
    }
}