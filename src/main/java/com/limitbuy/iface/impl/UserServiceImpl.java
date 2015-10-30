package com.limitbuy.iface.impl;

import com.limitbuy.dao.UserDao;
import com.limitbuy.entity.User;
import com.limitbuy.iface.UserService;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by longwu on 15/10/28.
 */
@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    public String register(User user) {
        User exists = userDao.findByUsername(user.getUsername());
        if(exists == null){
            return "user is exists!";
        }
        try{
            long result = userDao.saveUser(user);
            if(result  == 1){
                return "regist success,username:"+user.getUsername();
            }else{
                return "user information is incorrect !";
            }
        }catch (Exception e ){
            log.error(e.toString());
            return "user information is incorrect !";
        }
    }

    public String login(String username,String password) {
        User user = userDao.findByUsername(username);
        if(null != user){
            if(user.getPassword().equals(password)){
                return "login success!";
            }else{
                return "password is incorrect";
            }
        }else{
            return "user is not exists!";
        }
    }
}
