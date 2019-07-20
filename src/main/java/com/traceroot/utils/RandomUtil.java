package com.traceroot.utils;

import org.springframework.util.StringUtils;

import java.util.Random;

public class RandomUtil {

    /**
     * 用于生成随机的地理位置
     * @return
     */
    public static String genUniqueLocation(){
        Random random = new Random() ;
        //正负号生成
        Integer signLng = random.nextInt(2);
        Integer signLat = random.nextInt(2);
        String signLongtitude;
        String signLatitude;
        if (signLng == 0){
            signLongtitude = "-";
        }else{
            signLongtitude = "+";
        }
        if (signLat == 0){
            signLatitude = "-";
        }else{
            signLatitude = "+";
        }
        //经度生成
        Integer intlongtitude = random.nextInt(180);
        //纬度生成
        Integer intlatitude = random.nextInt(90);;

        String longtitude = new String("(" + signLongtitude+ intlongtitude + "." + System.currentTimeMillis()).substring(0,10);
        String latitude = new String("," + signLatitude + intlatitude + "." + System.currentTimeMillis()).substring(0,10);;

        return longtitude + latitude + ")";
    }

    /**
     * 用于生成随机的id
     * @return
     */
    public static String genUniqueId(){
        Random random = new Random() ;
        Integer number = random.nextInt(9000) + 1000;
        return new String(System.currentTimeMillis() + String.valueOf(number)).substring(10);
    }
}
