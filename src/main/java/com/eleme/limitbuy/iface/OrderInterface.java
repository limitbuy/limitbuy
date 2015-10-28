package com.eleme.limitbuy.iface;

import com.eleme.limitbuy.entity.Order;

/**
 * Created by longwu on 15/10/28.
 */
public interface OrderInterface {
    String orderPay(Order order);
    String orderCancel(String orderId);
}
