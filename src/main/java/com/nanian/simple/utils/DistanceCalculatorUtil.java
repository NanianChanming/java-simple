package com.nanian.simple.utils;

/**
 * @Author Caoheng
 * @Date 2023/5/9 9:54
 * @Description 给定经纬度计算坐标之间距离
 */
public class DistanceCalculatorUtil {
    private static final double EARTH_RADIUS = 6371000; // 地球半径，单位为米

    public static double distance(double latitudeFrom, double longitudeFrom, double latitudeTo, double longitudeTo) {
        double radLat1 = Math.toRadians(latitudeFrom);
        double radLat2 = Math.toRadians(latitudeTo);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(longitudeFrom) - Math.toRadians(longitudeTo);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s); // 将距离保留整数
        return s;
    }

    public static void main(String[] args) {
        double distance = distance(39.9, 116.4, 31.2, 121.4);
        System.out.println("两个经纬度之间的距离为：" + distance + "米");
    }
}
