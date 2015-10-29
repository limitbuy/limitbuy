package com.limitbuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenjie on 15/10/29.
 */
@Controller
@RequestMapping("/buy")
public class BuyAction {
    @ResponseBody
    @RequestMapping(value = "/git",method = RequestMethod.GET)
    public String returnJ(){

        return "sss";
    }
}
