package com.limitbuy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by longwu on 15/11/3.
 */
@Repository
public class LockCache {

    @Autowired
    private RedisCacheDao redisCacheDao;
    private static final Logger log = LoggerFactory.getLogger(LockCache.class);
    /**
     * Lock expiration in miliseconds 锁超时，防止线程在入锁以后，无限的执行等待
     */
    private int timeout = 500;

    /**
     * 锁资源
     *
     * @param key
     * @return true获取锁 反之没有获取锁
     * @throws InterruptedException
     */
    public boolean getLock(final String key)
            throws InterruptedException {
        String keySeed = "lock:" + key;
        String value = System.currentTimeMillis() + "|" + timeout;
        return redisCacheDao.setNx(keySeed,value);

        /*try {
            while (true) {
                setnxResult = redisCacheDao.setNx(keySeed,value);
                if (setnxResult) {
                    redisCacheDao.expire(keySeed,1);
                    return true;
                } else  {
                    return false;
                    *//*returnValue = redisCacheDao.get(keySeed);
                    long currentTime = System.currentTimeMillis();
                    while (true) {
                        splitString = returnValue.split("[|]");
                        if (currentTime < Long.parseLong(splitString[0])
                                + Long.parseLong(splitString[1])) {
                            if (++tRetry > retry)
                                return false;
                            Thread.sleep(sleepTime);
                            returnValue = redisCacheDao.get(keySeed);
                            currentTime = System.currentTimeMillis();
                            continue;
                        } else {
                            value = System.currentTimeMillis() + "|" + timeout;
                            String oldValue = redisCacheDao.getSet(keySeed,value);
                            if (returnValue.equals(oldValue)) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }*//*
                }
            }
        } catch (Exception e) {
            log.error("[LockCacheImpl]->[lock] error:[keySeed]=" + keySeed, e);
            return false;
        }*/
    }

    /**
     * 释放资源
     *
     * @param key
     */
    public void releaseLock(final String key) {
        try {
            if (redisCacheDao.existsKey(key));
                redisCacheDao.delKey(key);
        } catch (Exception e) {
            log.error("", e);
        }
    }


}
