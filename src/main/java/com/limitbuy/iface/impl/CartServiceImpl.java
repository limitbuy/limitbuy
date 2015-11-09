package com.limitbuy.iface.impl;

import com.alibaba.fastjson.JSONObject;
import com.limitbuy.dao.GoodsDao;
import com.limitbuy.dao.RedisCacheDao;
import com.limitbuy.entity.Cart;
import com.limitbuy.entity.Goods;
import com.limitbuy.iface.CartServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenji on 15/10/28.
 */
@Service
public class CartServiceImpl implements CartServie{
    private ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private RedisCacheDao redisCacheDao;

    public void addToCart(String username,Goods goods) {
        final Map cart = new HashMap();
        cart.put("userName",username);
        cart.put("productId",goods.getProductId());
        cart.put("count", goods.getCount());
        executorService.submit(new Runnable() {
            public void run() {
                goodsDao.insertCart(cart);
            }
        });
        List<Goods> goodsList = null;
        String userInfo = redisCacheDao.getUserInfo(username);
        if(!userInfo.equals(username)){
            goodsList = JSONObject.parseArray(userInfo,Goods.class);
            for (Goods goods1 : goodsList){
                if(goods1.getProductId() ==  goods.getCount()){
                    goods1.setCount(goods1.getCount() + goods.getCount());
                    redisCacheDao.setUserInfo(username, JSONObject.toJSONString(goodsList));
                    break;
                }
            }
            goodsList.add(goods);
            redisCacheDao.setUserInfo(username, JSONObject.toJSONString(goodsList));
        }else{
            goodsList = new ArrayList<Goods>();
            goodsList.add(goods);
            redisCacheDao.setUserInfo(username, JSONObject.toJSONString(goodsList));
        }
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
