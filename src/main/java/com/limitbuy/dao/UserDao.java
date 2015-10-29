package com.limitbuy.dao;

import com.limitbuy.entity.User;

/**
 * Created by longwu on 15/10/29.
 */
public interface UserDao {

    long saveUser(User user);

    User findByUsername(String username);
}
