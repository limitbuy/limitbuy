package com.limitbuy.dao;

import com.limitbuy.entity.Order;
import com.limitbuy.entity.OrderGoods;

import java.util.List;
import java.util.Map;

/**
 * Created by chenjie on 15/11/3.
 */
public interface OrderDao {

    public int insert(Order order);

    public int insertOrderGoods(OrderGoods orderGoods);

    public List<String> queryOrderId(String userName);

    public  List<OrderGoods> queryAllGoods(Map map);

    List<String> queryDistinctName();
}
