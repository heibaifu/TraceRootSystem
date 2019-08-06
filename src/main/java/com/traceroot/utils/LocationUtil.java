package com.traceroot.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LocationUtil {

    /**
     * 将经纬度坐标的字符串表示转化为DoubleLocation类型返回
     * @param location
     * @return
     */
    public static DoubleLocation string2doubleLocation(String location){
        //经度 Longitude 简写Lng 纬度 Latitude 简写Lat
        double Lng, Lat;
        String[] strings = location.split(",");
        Lng = Double.parseDouble(strings[0].substring(1)); //拿取经度
        Lat = Double.parseDouble(strings[1].substring(0,strings[1].length()-1)); //拿取纬度
        return new DoubleLocation(Lng,Lat);
    }

    /**
     * 将DoubleLocation类型的坐标数据转化为String类型返回
     * @param location
     * @return
     */
    public static String doubleLocation2String(DoubleLocation location){
        //经度 Longitude 纬度 Latitude
        String result = "(" + location.getLongitude() +"," + location.getLatitude() + ")";
        return result;
    }


    /**
     * 将经纬度坐标的字符串表示转化为前端需要的格式类型返回
     * 根据前端需求去掉括号
     * @param location
     * @return "精度,纬度"
     */
    public static String string2DTOstring(String location){
        //经度 Longitude 简写Lng 纬度 Latitude 简写Lat
        double Lng, Lat;
        String[] strings = location.split(",");
        Lng = Double.parseDouble(strings[0].substring(1)); //拿取经度
        Lat = Double.parseDouble(strings[1].substring(0,strings[1].length()-1)); //拿取纬度
        String result = Lng+ ","+ Lat;
        return result;
    }

    /**
     * 构建模糊匹配表达式
     * @param lng 经度
     * @param lat 纬度
     * @param accuracyDegree 精度，即小数点后匹配开始的位数 exclusive
     * @return
     */
    public static String buildFuzzyMatchingExpr(Double lng, Double lat, Integer accuracyDegree){

        String lngString = lng.toString();
        String latString = lat.toString();

        String result;

        //例子："(+116.3%,+39.9%)"

        //拿出经度小数点前的位数
        String[] split = lngString.split("\\.");
        String substring = split[1].substring(0, accuracyDegree);
        //拼接精度
        if(lng > 0)
            result = "(+" + split[0] + "." + substring + "%" + ",";
        else
            result = "(" + split[0] + "." + substring + "%" + ",";

        //重复上一部，拿纬度数据
        split = latString.split("\\.");
        substring = split[1].substring(0, accuracyDegree);
        if(lat > 0)
            result = result + "+" + split[0] + "." + substring + "%" + ")";
        else
            result = result +  split[0] + "." + substring + "%" + ")";

        return result;
    }
}
