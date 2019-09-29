package com.adwyxx.springboot.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.concurrent.TimeUnit;

/**
 * Redis Distributed Lock
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-29 13:43
 */
public class RedisLock {
    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    //Redis Template Instance
    @Autowired
    private static StringRedisTemplate redisTemplate;

    /**
     * version 1.0 : 实现分布式锁，但是非线程安全的
     */
    public static boolean tryLock(String lockName,String value){
        //1. 通过使用setIfAbsent(key,value)方法设置,其底层使用的是SETNX命令
        //如果键不存在则新增,存在则不改变已经有的值
        boolean success = redisTemplate.opsForValue().setIfAbsent(lockName,value);
        return success;
    }

    /**
     * 尝试获取锁
     * @param lockName: 锁名称
     * @param value: 锁的值
     * @param timeout: 锁超时时间，单位：毫秒
     * @return boolean: 获取锁是否成功
     */
    public static boolean tryLock(String lockName,String value,long timeout){
       return redisTemplate.opsForValue().setIfAbsent(lockName,value,timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 尝试获取锁
     * @param lockName: 锁名称
     * @param value: 锁的值
     * @param timeout: 锁超时时间，单位：毫秒
     * @param tryInterval: 每个多少毫秒尝试一次获取锁，直到获取成功为止
     * @return boolean: 获取锁是否成功
     */
    public static boolean tryLock(String lockName,String value,long timeout,long tryInterval) throws InterruptedException {
        boolean success = false;
        do {
            success = redisTemplate.opsForValue().setIfAbsent(lockName,value,timeout, TimeUnit.MILLISECONDS);
            if(success)
            {
                return success; //获取到锁则立即返回。
            }
            TimeUnit.MILLISECONDS.sleep(tryInterval); //休眠一段时间后继续try
        } while (!success);
        return success;
    }

    /**
     * 解锁，根据key和value 删除 Redis 中数据
     * @param key: key
     * @param value: value
     */
    public static void unlock(String key,String value){
        String oldValue = redisTemplate.opsForValue().get(key);
        //value值相等时才执行删除操作
        if((value == null && oldValue == null) || oldValue.equals(value))
        {
            redisTemplate.delete(key);
        }
    }
}

