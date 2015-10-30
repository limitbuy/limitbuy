package com.limitbuy.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by longwu on 15/10/30.
 */
@Repository
public class RedisCacheDao {
    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    private static final String USER_INFO = "token:";

    public void setUserInfo(String username){
        if(!redisTemplate.opsForSet().isMember(USER_INFO + username, username)){
            redisTemplate.opsForSet().add(USER_INFO+username,username);
            redisTemplate.expire(USER_INFO+username,12, TimeUnit.HOURS);
        }
    }

    public boolean isExistsUser(String  username){
        return redisTemplate.opsForSet().isMember(USER_INFO + username, username);
    }

}
