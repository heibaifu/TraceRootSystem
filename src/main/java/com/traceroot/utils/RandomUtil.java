package com.traceroot.utils;

import org.springframework.util.StringUtils;

import java.util.Random;

public class RandomUtil {

    /**
     * 用于生成随机的地理位置，这是带括号的
     * @return
     */
    public static String genUniqueLocation(){
        Random random = new Random() ;
        //正负号生成
        Integer signLng = random.nextInt(2);
        Integer signLat = random.nextInt(2);
        Integer signLongtitude;
        Integer signLatitude;
        if (signLng == 0){
            signLongtitude = 1;
        }else{
            signLongtitude = -1;
        }
        if (signLat == 0){
            signLatitude = 1;
        }else{
            signLatitude = -1;
        }
        //经度生成
        Integer intlongtitude = random.nextInt(180);
        //纬度生成
        Integer intlatitude = random.nextInt(90);;

        String longtitude = new String(signLongtitude * intlongtitude + "." + System.currentTimeMillis()).substring(0,10);
        String latitude = new String(signLatitude * intlatitude + "." + System.currentTimeMillis()).substring(0,10);;

        return longtitude + "," + latitude;
    }

    /**
     * 用于生成随机的id
     * @return
     */
    public static String genUniqueId(){
        //todo 要保证id唯一
        Random random = new Random() ;
        Integer number = random.nextInt(9000) + 1000;
        return new String(System.currentTimeMillis() + String.valueOf(number)).substring(10);
    }
}
