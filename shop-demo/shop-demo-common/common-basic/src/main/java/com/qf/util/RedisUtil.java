package com.qf.util;/*
    @auter wwx
    @date 2020/3/10
*/

public class RedisUtil {
    public static String getRedisKey(String pre,String key){
        return new StringBuilder().append(pre).append(key).toString();
    }
}
