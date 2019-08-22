package com.traceroot.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GeographyUtil {

    /**
     * 将经纬度坐标的字符串表示转化为DoubleLocation类型返回
     * @param location
     * @return
     */
    public static DoubleLocation string2doubleLocation(String location){
        //经度 Longitude 简写Lng 纬度 Latitude 简写Lat
        double Lng, Lat;
        String[] strings = location.split(",");
        Lng = Double.parseDouble(strings[0]); //拿取经度
        Lat = Double.parseDouble(strings[1]); //拿取纬度
        return new DoubleLocation(Lng,Lat);
    }

    /**
     * 将DoubleLocation类型的坐标数据转化为String类型返回
     * @param location
     * @return
     */
    public static String doubleLocation2String(DoubleLocation location){
        //经度 Longitude 纬度 Latitude
        String result = location.getLongitude() +"," + location.getLatitude();
        return result;
    }


    /**
     * 将经纬度坐标的字符串表示转化为前端需要的格式类型返回
     * 根据前端需求去掉括号
     * @param location (精度,纬度)，带正负号
     * @return "精度,纬度"
     */
    @Deprecated
    public static String string2DTOstring(String location){
        //经度 Longitude 简写Lng 纬度 Latitude 简写Lat
        double Lng, Lat;
        String[] strings = location.split(",");
        Lng = Double.parseDouble(strings[0]); //拿取经度
        Lat = Double.parseDouble(strings[1]); //拿取纬度
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

        //例子："116.3%,-39.9%"

        if (accuracyDegree > 0){
            //拿出经度小数点前的位数
            String[] split = lngString.split("\\.");
            String substring = split[1].substring(0, accuracyDegree);
            //拼接精度
            result = split[0] + "." + substring + "%" + ",";

            //重复上一部，拿纬度数据
            split = latString.split("\\.");
            substring = split[1].substring(0, accuracyDegree);

            result = result +  split[0] + "." + substring + "%";

        } else {
            //拿出经度小数点前的位数
            String[] split = lngString.split("\\.");
            //拼接精度
            result = split[0].substring(0, split[0].length()-1) + "%";
            //重复上一部，拿纬度数据
            split = latString.split("\\.");
            result = result + "," +split[0].substring(0, split[0].length()-1) + "%";
        }

        return result;
    }

    /**
     * 求用指定起始位置表示的向量与正北方向的夹角
     * @param startDirection
     * @param endDirection
     * @return 弧度值
     */
    public static double inferDirection(String startDirection, String endDirection){

        String[] startString = startDirection.split(",");
        DoubleLocation start = new DoubleLocation(new Double(startString[0]),new Double(startString[1]));
        String[] endString = endDirection.split(",");
        DoubleLocation end = new DoubleLocation(new Double(endString[0]),new Double(endString[1]));

        //构建向量，基基底都是单位向量
        DoubleLocation vector = new DoubleLocation((end.getLongitude() - start.getLongitude())/2,end.getLatitude()- start.getLatitude());

        //与(0,1)做数量积
        double dotProduct = vector.getLatitude() * 1;

        //求模值
        double norm = Math.sqrt(Math.pow(vector.getLongitude(),2) + Math.pow(vector.getLatitude(),2) );

        double radian;
        if (vector.getLongitude() > 0){
            radian = Math.acos(dotProduct / norm);
        } else {
            radian = 2 * Math.PI - Math.acos(dotProduct / norm) ;
        }

        return radian;
    }
}
