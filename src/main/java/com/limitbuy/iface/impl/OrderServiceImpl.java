package com.limitbuy.iface.impl;

import com.alibaba.fastjson.JSONObject;
import com.limitbuy.dao.OrderDao;
import com.limitbuy.dao.RedisCacheDao;
import com.limitbuy.entity.Goods;
import com.limitbuy.entity.Order;
import com.limitbuy.entity.OrderGoods;
import com.limitbuy.iface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longwu on 15/10/28.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderDao orderDao;
    @Autowired
    private RedisCacheDao redisCacheDao;

    public String insert(String username) {
        String userInfo = redisCacheDao.getUserInfo(username);
        if (userInfo.equals(username)) {
            return "您的购物车是空的,请先添加购物车!";
        } else {
            List<Goods> goodsList = JSONObject.parseArray(userInfo, Goods.class);
            Order order = new Order();
            order.setProductList(goodsList);
            order.setUserName(username);
            orderDao.insert(order);
            int id = order.getId();
            List<Goods> list = order.getProductList();
            if (list != null && list.size() > 0) {
                for (Goods p : list) {
                    OrderGoods og = new OrderGoods();
                    og.setOrderId(id);
                    og.setProductId(p.getProductId());
                    og.setCount(p.getCount());
                    orderDao.insertOrderGoods(og);
                }
            }
            redisCacheDao.setUserInfo(username,username);
        }
        return null;
    }


    public Order queryOrder(String userName) {

        List<String> orderIds = orderDao.queryOrderId(userName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderIds", orderIds);
        List<OrderGoods> ogs = orderDao.queryAllGoods(map);
        List<Goods> goods = new ArrayList<Goods>();
        for (OrderGoods pg : ogs) {
            Goods g = new Goods();
            g.setCount(pg.getCount());
            g.setProductId(pg.getProductId());
            goods.add(g);
        }
        Order o = new Order();
        o.setUserName(userName);
        o.setProductList(goods);
        return o;
    }

    public List<Order> queryAll() {
        List<Order> orders = new ArrayList<Order>();
        List<String> usernames = orderDao.queryDistinctName();
        for (String username : usernames) {
            orders.add(queryOrder(username));
        }
        return orders;
    }
}
