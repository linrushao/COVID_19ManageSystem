package com.linrs.webui.redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author LRS
 * @Date 2022/8/13 22:00
 * Desc
 */
public class TestConnRedis {

    public static void main(String[] args) {
        //连接本地Redis服务
        Jedis jedis = new Jedis("localhost");
        //如果 Redis 服务设置了密码，需要下面这行代码，没有设置密码就不需要
//        jedis.auth("123456");
//        System.out.println("连接成功");
//        //查看服务是否运行
//        System.out.println("服务正在运行："+jedis.ping());

        //1.String类型【确诊：13，死亡：12】
        jedis.set("nocv","linrs");

        //2.List集合【新闻集合】
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
        System.out.println("=========================");

        //3.无序集合 去重
        // 获取数据并输出
        jedis.sadd("nocvset","111");
        jedis.sadd("nocvset","111");
        jedis.sadd("nocvset","111");
        jedis.sadd("nocvset","111");
        jedis.sadd("nocvset","111");
        jedis.sadd("nocvset","222");
        jedis.sadd("nocvset","222");
        Set<String> noceset = jedis.smembers("nocvset");
        for (String s:noceset){
            System.out.println(s);
        }

        System.out.println("=========================");
        //4.sorted set有序集合【排名，排序，获取排序码】
        jedis.zadd("nocvset2",23,"111");
        jedis.zadd("nocvset2",32.3,"222");
        jedis.zadd("nocvset2",65.4,"333");
        jedis.zadd("nocvset2",56,"444");
        jedis.zadd("nocvset2",87,"555");
        Set<String> nocvset2 = jedis.zrange("nocvset2", 0, -1);
        for (String s:nocvset2){
            System.out.println(s);
        }
        System.out.println("=========================");
        Long nocvset21 = jedis.zrank("nocvset2", "333");
        System.out.println(nocvset21);
        System.out.println("=========================");
        //返回分数区间内的个数
        Long nocvset22 = jedis.zremrangeByScore("nocvset2", 32, 50);

        //返回有序集合,成员的分数值
        Double nocvset23 = jedis.zscore("nocvset2", "444");
    }


}
