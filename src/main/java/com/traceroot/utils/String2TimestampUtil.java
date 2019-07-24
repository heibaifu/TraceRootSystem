package com.traceroot.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class String2TimestampUtil {

    /**
     * 将字符串类型的日期转换为一个timestamp
     * @param dateString 需要转换为timestamp的字符串
     * @return
     * @throws java.text.ParseException
     */
    public final static Timestamp string2Time(String dateString)
            throws java.text.ParseException {
        DateFormat dateFormat;
//        dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH); //设定格式
        dateFormat.setLenient(false);
        Date timeDate = dateFormat.parse(dateString);   //util类型
        Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());    //Timestamp类型,timeDate.getTime()返回一个long型
        return dateTime;
    }

}
