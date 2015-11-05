package com.limitbuy.iface;

import com.limitbuy.entity.Order;

import java.util.List;

/**
 * Created by longwu on 15/10/28.
 */
public interface OrderService {

  public  void insert(Order order);

  public  Order queryOrder(String userName);

  List<Order> queryAll();
}



