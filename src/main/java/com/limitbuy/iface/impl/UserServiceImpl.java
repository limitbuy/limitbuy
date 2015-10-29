package com.limitbuy.iface.impl;

import com.limitbuy.dao.UserDao;
import com.limitbuy.entity.User;
import com.limitbuy.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by longwu on 15/10/28.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    public String register(User user) {
        return userDao.saveUser(user)==1?"success":"failure";
    }

    public String login(String username,String password) {
        User user = userDao.findByUsername(username);
        if(null != user){
            if(user.getPassword().equals(password)){
                return "login success!";
            }else{
                return "password is wrong";
            }
        }else{
            return "user is not exsits";
        }
    }
}
