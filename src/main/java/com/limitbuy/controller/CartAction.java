package com.limitbuy.controller;

import com.limitbuy.entity.Cart;
import com.limitbuy.iface.CartServie;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

/**
 * Created by chenjie on 15/10/30.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartAction {

    @Autowired
    public CartServie cartServie;

    @ResponseBody
    @RequestMapping(value = "/insertCart", method = RequestMethod.GET)
    public String insertCart(@ModelAttribute Cart cart) {
        System.out.print(cart.toString());
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/cancleCart", method = RequestMethod.GET)
    public String canceleCart(String userName) {
       cartServie.deleteFromCart(userName);
        return userName;

    }
}
