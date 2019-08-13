package com.traceroot.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomUtilTest {

    @Test
    public void genUniqueLocation() {
        String uniqueLocation = RandomUtil.genUniqueLocation();
        Assert.assertNotNull(uniqueLocation);
    }

    @Test
    public void genUniqueId() {
    }
}