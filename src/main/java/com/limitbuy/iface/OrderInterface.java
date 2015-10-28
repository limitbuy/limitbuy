package com.limitbuy.iface;

import com.limitbuy.entity.Order;

/**
 * Created by longwu on 15/10/28.
 */
public interface OrderInterface {
    String orderPay(Order order);
    String orderCancel(String orderId);
}
