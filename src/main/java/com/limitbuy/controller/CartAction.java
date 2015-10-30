package com.limitbuy.controller;

import com.alibaba.fastjson.JSONArray;
import com.limitbuy.entity.Cart;
import com.limitbuy.entity.Goods;
import com.limitbuy.iface.CartServie;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjie on 15/10/30.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartAction {

    @Autowired
    public CartServie cartServie;

    @ResponseBody
    @RequestMapping(value = "/insertCart", method = RequestMethod.POST)
    public String insertCart(@RequestBody Cart cart) {
        List<Goods> list = cart.getProductList();
        for(Goods g:list){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("userName",cart.getUserName());
            map.put("count",g.getCount());
            map.put("productId", g.getProductId());
            cartServie.addToCart(map);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/cancleCart", method = RequestMethod.GET)
    public String canceleCart(String userName) {
        System.out.print(userName);
       cartServie.deleteFromCart(userName);
        return userName;

    }
}
