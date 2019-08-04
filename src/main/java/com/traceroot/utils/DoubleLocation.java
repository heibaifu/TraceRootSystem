package com.traceroot.utils;

import lombok.Data;

/**
 * 经纬度坐标值
 */

@Data
public class DoubleLocation {

    //经度
    private double Longitude;

    //纬度
    private double Latitude;

    public DoubleLocation() {
    }

    public DoubleLocation(double longitude, double latitude) {
        Longitude = longitude;     //经度
        Latitude = latitude;    //纬度
    }
}
