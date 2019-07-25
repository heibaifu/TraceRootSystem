package com.traceroot.utils;

public class LocationUtil {

    /**
     * 将经纬度坐标的字符串表示转化为DoubleLocation类型返回
     * @param segmentStartLocation
     * @return
     */
    public static DoubleLocation string2doubleLocation(String segmentStartLocation){
        //经度 Longitude 简写Lng 纬度 Latitude 简写Lat
        double Lng, Lat;
        String[] strings = segmentStartLocation.split(",");
        Lng = Double.parseDouble(strings[0].substring(1)); //拿取经度
        Lat = Double.parseDouble(strings[1].substring(0,strings[1].length()-1)); //拿取纬度
        return new DoubleLocation(Lng,Lat);
    }
}
