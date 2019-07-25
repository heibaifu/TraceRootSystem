package com.traceroot.utils;

/**
 * 数学工具
 */

public class MathUtil {

    /**
     * 判断两线段【经纬度描述】是否相交
     * @param l1Start
     * @param l1End
     * @param l2Start
     * @param l2End
     * @return 是否相交
     */
    public static boolean intersection(DoubleLocation l1Start, DoubleLocation l1End, DoubleLocation l2Start, DoubleLocation l2End)
    {
        double l1x1 = l1Start.getLongitude();
        double l1y1 = l1Start.getLatitude();
        double l1x2 = l1End.getLongitude();
        double l1y2 = l1End.getLongitude();;
        double l2x1 = l2Start.getLongitude();
        double l2y1 = l2Start.getLongitude();;
        double l2x2 = l2End.getLongitude();
        double l2y2 = l2End.getLongitude();;

        // 快速排斥实验 首先判断两条线段在 x 以及 y 坐标的投影是否有重合。 有一个为真，则代表两线段必不可交。
        if (Math.max(l1x1,l1x2) < Math.min(l2x1 ,l2x2)
                || Math.max(l1y1,l1y2) < Math.min(l2y1,l2y2)
                || Math.max(l2x1,l2x2) < Math.min(l1x1,l1x2)
                || Math.max(l2y1,l2y2) < Math.min(l1y1,l1y2))
        {
            return false;
        }
        // 跨立实验  如果相交则矢量叉积异号或为零，大于零则不相交
        if ((((l1x1 - l2x1) * (l2y2 - l2y1) - (l1y1 - l2y1) * (l2x2 - l2x1))
                * ((l1x2 - l2x1) * (l2y2 - l2y1) - (l1y2 - l2y1) * (l2x2 - l2x1))) > 0
                || (((l2x1 - l1x1) * (l1y2 - l1y1) - (l2y1 - l1y1) * (l1x2 - l1x1))
                * ((l2x2 - l1x1) * (l1y2 - l1y1) - (l2y2 - l1y1) * (l1x2 - l1x1))) > 0)
        {
            return false;
        }
        return true;
    }
}
