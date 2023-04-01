package com.lrs.util;


import org.apache.commons.lang3.time.FastDateFormat;

//时间工具类
public abstract class TimeUtils {

    public static String format(Long timestamp, String pattern) {
        return FastDateFormat.getInstance(pattern).format(timestamp);
    }

    public static void main(String[] args) {
        String format = TimeUtils.format(System.currentTimeMillis(), "yyy-MM-dd");
        System.out.println(format);
    }


}
