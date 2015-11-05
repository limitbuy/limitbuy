package com.limitbuy.controller;

import com.alibaba.fastjson.JSONObject;
import com.limitbuy.entity.User;
import com.limitbuy.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by longwu on 15/10/30.
 */
@RestController
@RequestMapping("/user")
public class UserAction {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String regist(@RequestBody User user){
        return userService.register(user);
    }

    /**
     * 登录z
     * @param user
     * @return
     */
    @RequestMapping(value="login",method=RequestMethod.POST)
    public String login(@RequestBody User user){
        return userService.login(user.getUsername());
    }
}
