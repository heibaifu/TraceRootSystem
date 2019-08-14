package com.traceroot.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    /**
     * 将字符串类型的日期转换为一个timestamp
     * @param dateString 需要转换为timestamp的字符串
     * @return
     * @throws java.text.ParseException
     */
    public final static Timestamp string2Timestamp(String dateString)
            throws java.text.ParseException {
        DateFormat dateFormat;
//        dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH); //设定格式
        dateFormat.setLenient(false);
        Date timeDate = dateFormat.parse(dateString);   //util类型
        Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());    //Timestamp类型,timeDate.getTime()返回一个long型
        return dateTime;
    }

    /**
     * 获取当前系统时间
     * @return
     */
    public final static String presentTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return String.valueOf(df.format(new Date()));
    }

    /**
     * 获取过去第几天的日期
     * @param past
     * @return
     */
    public static String getPastDateBeforePresent(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = format.format(today);
        return result;
    }


    /**
     * 获取指定时间前几天的时间
     * @param thatDay
     * @param day
     * @return
     */
    public static String getPastDateBeforeThatDay(Date thatDay, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(thatDay);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);//+后 -前
        Date resultTime = now.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = format.format(resultTime);
        return result;
    }
}
