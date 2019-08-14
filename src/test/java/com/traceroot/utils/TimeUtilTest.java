package com.traceroot.utils;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class TimeUtilTest {

    @Test
    public void getPastDateBeforeThatDay() {
        try {
            String pastDateBeforeThatDay = TimeUtil.getPastDateBeforeThatDay(TimeUtil.string2Timestamp("2019-08-14 12:33:35"), 3);
            Assert.assertNotNull(pastDateBeforeThatDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}