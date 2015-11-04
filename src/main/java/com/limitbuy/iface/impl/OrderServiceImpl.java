package com.limitbuy.iface.impl;

import com.limitbuy.dao.OrderDao;
import com.limitbuy.entity.Goods;
import com.limitbuy.entity.Order;
import com.limitbuy.entity.OrderGoods;
import com.limitbuy.entity.Product;
import com.limitbuy.iface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by longwu on 15/10/28.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderDao orderDao;

    public int insert(Order order) {
        orderDao.insert(order);
        int id = order.getId();
        List<Goods> list = order.getProductList();
        if (list != null && list.size() > 0) {
            for (Goods p : list) {
                OrderGoods og = new OrderGoods();
                og.setOrderId(id);
                og.setProductId(p.getProductId());
                og.setCount(p.getCount());
            }
        }
        return  1;
    }


    public Order queryOrder(String userName) {

        List<String> orderIds = orderDao.queryOrderId(userName);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orderIds",orderIds);
        List<OrderGoods> ogs = orderDao.queryAllGoods(map);
        List<Goods> goods = new ArrayList<Goods>();
        for(OrderGoods pg:ogs){
            Goods g =new Goods();
            g.setCount(pg.getCount());
            g.setProductId(pg.getProductId());
            goods.add(g);
        }
        Order o = new Order();
        o.setUserName(userName);
        o.setProductList(goods);
        return o;
    }
}
