package com.traceroot.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
@Slf4j
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

    //赤道半径(单位m)
    private static double EARTH_RADIUS = 6378137;

    //转化为弧度(rad)
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为m）
     * @param start
     * @param end
     * @return 两点的距离，double类型
     */
    public static double getDistance(DoubleLocation start, DoubleLocation end) {

        double radLat1 = rad(start.getLatitude());
        double radLat2 = rad(end.getLatitude());

        double radLon1 = rad(start.getLongitude());
        double radLon2 = rad(end.getLongitude());

        if (radLat1 < 0)
            radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
        if (radLat1 > 0)
            radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
        if (radLon1 < 0)
            radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
        if (radLat2 < 0)
            radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
        if (radLat2 > 0)
            radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
        if (radLon2 < 0)
            radLon2 = Math.PI * 2 - Math.abs(radLon2);// west
        double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = EARTH_RADIUS * Math.cos(radLat1);

        double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = EARTH_RADIUS * Math.cos(radLat2);

        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));
        //余弦定理求夹角
        double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
        double distance = theta * EARTH_RADIUS;
        return distance;
    }


    /**
     * 根据两点经纬度计算速度
     */
    public static String getSpeed(String startDirection, String endDirection, Date startTime,Date endTime){

        DoubleLocation start = string2doubleLocation(startDirection);
        DoubleLocation end = string2doubleLocation(endDirection);
        Double distance=GeographyUtil.getDistance(start,end);
        Double time=TimeUtil.getTimeDiff(startTime,endTime);
        Double speed=distance/time;
        DecimalFormat df = new DecimalFormat("0.000");   //保留三位小数
        return df.format(speed);
    }

    /**
     * 点到直线的最短距离的判断
     * 点point到由两点start、end组成的线段
     * 数学原理：线段与一点构成的三角形进行运算
     * @param start
     * @param end
     * @param point
     * @return
     */
    public static Double pointToLine( DoubleLocation start, DoubleLocation end, DoubleLocation point) {
        double result;
        double lengthOfLine = getDistance(start,end);  //线段的长度
        double distanceFromStartToPoint = getDistance(start,point);  // start到点的距离
        double distanceFromEndToPoint = getDistance(end,point);    // end到点的距离
        //点非常靠近起点和终点
        if (distanceFromEndToPoint <= 0.000001 || distanceFromStartToPoint <= 0.000001) {
            result = 0;
            return result;
        }
        //很短的线段，几乎是一个点
        if (lengthOfLine <= 0.000001) {
            result = distanceFromStartToPoint;
            return result;
        }
        //组成直角三角形或钝角三角形，start为直角或钝角
        if (distanceFromEndToPoint * distanceFromEndToPoint >= lengthOfLine * lengthOfLine + distanceFromStartToPoint * distanceFromStartToPoint) {
            result = distanceFromStartToPoint;
            return result;
        }
        //组成直角三角形或钝角三角形，end为直角或钝角
        if (distanceFromStartToPoint * distanceFromStartToPoint >= lengthOfLine * lengthOfLine + distanceFromEndToPoint * distanceFromEndToPoint) {
            result = distanceFromEndToPoint;
            return result;
        }
        //组成锐角三角形，则求三角形的高
        double p = (lengthOfLine + distanceFromStartToPoint + distanceFromEndToPoint) / 2;  //半周长
        double s = Math.sqrt(p * (p - lengthOfLine) * (p - distanceFromStartToPoint) * (p - distanceFromEndToPoint));   //海伦公式求面积
        result = 2 * s / lengthOfLine;  //利用三角形面积公式求高，返回点到线的距离
        return result;
    }

}
