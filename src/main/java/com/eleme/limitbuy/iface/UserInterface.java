package com.eleme.limitbuy.iface;

import com.eleme.limitbuy.entity.User;

/**
 * Created by longwu on 15/10/28.
 */
public interface UserInterface {
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
    String login(User user);


}
