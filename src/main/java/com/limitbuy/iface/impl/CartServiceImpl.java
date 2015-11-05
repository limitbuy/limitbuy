package com.limitbuy.iface.impl;

import com.alibaba.fastjson.JSONObject;
import com.limitbuy.dao.GoodsDao;
import com.limitbuy.dao.RedisCacheDao;
import com.limitbuy.entity.Cart;
import com.limitbuy.iface.CartServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenji on 15/10/28.
 */
@Service
public class CartServiceImpl implements CartServie{

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private RedisCacheDao redisCacheDao;

    public void addToCart(Map cart) {
        goodsDao.insertCart(cart);
    }

    public String deleteFromCart(String userName)
    {
        goodsDao.cancleCart(userName);
        return null;
    }

    public String cart() {
        return null;
    }
}
