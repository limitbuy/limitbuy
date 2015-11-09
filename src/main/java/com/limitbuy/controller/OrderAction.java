package com.limitbuy.controller;

import com.alibaba.fastjson.JSONObject;
import com.limitbuy.dao.RedisCacheDao;
import com.limitbuy.entity.Goods;
import com.limitbuy.entity.Order;
import com.limitbuy.iface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenjie on 15/10/29.
 */
@RestController
@RequestMapping("/order")
public class OrderAction {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisCacheDao redisCacheDao;
    /**
     * 下单接口
     * @return
     */
    @RequestMapping(value = "/buy/{username}",method = RequestMethod.GET)
    public String buy(@PathVariable String username){
        if (redisCacheDao.isExistsUser(username)) {
            orderService.insert(username);
            return "下单成功!";
        }else{
            return "该用户没有登录,请先登录!";
        }
    }

    /**
     * 查询已下订单
     * @param username
     * @return
     */
    @RequestMapping(value = "/query/{username}",method = RequestMethod.GET)
    public String query(@PathVariable String username){
        Order order = orderService.queryOrder(username);
        return JSONObject.toJSONString(order);
    }

    /**
     * 查下系统所有订单
     * @return
     */
    @RequestMapping(value = "/query/all",method = RequestMethod.GET)
    public String queryAllOrders(){
        StringBuilder sb = new StringBuilder("系统所有订单: ");
        List<Order> orders = orderService.queryAll();
        for (Order order : orders){
            sb.append("\n用户名:"+ order.getUserName());
            int i = 0;
            for(Goods goods : order.getProductList()){
                sb.append(" ,订单" + (++i) + ": 商品id: " + goods.getProductId() + " ,商品数量: " + goods.getCount());
            }
        }
        return sb.toString();
    }

}
