package com.limitbuy.iface;

import com.limitbuy.entity.User;

/**
 * Created by longwu on 15/10/28.
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return
     */
    String register(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    String login(String username,String pasword);


}
