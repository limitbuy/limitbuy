package com.limitbuy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * Created by longwu on 15/10/30.
 */
@Repository
public class RedisCacheDao {
    private static final Logger log = LoggerFactory.getLogger(RedisCacheDao.class);
    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    private static final String USER_INFO = "userInfo";
    private static final String PRODUCT = "product";;

    public void setUserInfo(String username,String userinfo) {
        redisTemplate.opsForHash().put(USER_INFO,username,userinfo);
    }

    public String getUserInfo(String username){
        return (String)redisTemplate.opsForHash().get(USER_INFO,username);
    }

    public boolean isExistsUser(String username) {
       return redisTemplate.opsForHash().hasKey(USER_INFO,username);
    }

    public int queryGoodsCount(String productId){
        Object result = redisTemplate.opsForHash().get(PRODUCT,productId);
        if(result != null){
            return Integer.parseInt(result.toString());
        }else{
            return 0;
        }
    }

    public void setGoodsCount(String productId,String stock){
        redisTemplate.opsForHash().put(PRODUCT, productId, stock);
    }

    public boolean setNx(final String key, final String vallue) {
        Boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.setNX(key.getBytes(), vallue.getBytes());
            }
        });
        return result;
    }

    public String get(final String key) {
        String result = (String) redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                try {
                    return new String(redisConnection.get(key.getBytes()), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    log.error(e.toString());
                    return null;
                }
            }
        });
        return result;
    }

    public String getSet(final String key, final String value) {
        String result = (String) redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                try {
                    return new String(redisConnection.getSet(key.getBytes(), value.getBytes()), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    log.error(e.toString());
                    return null;
                }
            }
        });
        return result;
    }

    public void expire(String key, int timeSeconds) {
        redisTemplate.expire(key, timeSeconds, TimeUnit.SECONDS);
    }

    public boolean existsKey(final String key) {
        Boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.exists(key.getBytes());
            }
        });
        return result;
    }

    public void delKey(String key) {
        redisTemplate.delete(key);
    }

}
