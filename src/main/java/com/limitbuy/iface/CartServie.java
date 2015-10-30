package com.limitbuy.iface;

import com.limitbuy.entity.Cart;

import java.util.Map;

/**
 * Created by longwu on 15/10/28.
 */
public interface CartServie {

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    String addToCart(Map cart);

    /**
     * 删除购物车
     * @param productId
     * @return
     */
    String deleteFromCart(String productId);

    /**
     * 查看购物车
     * @return
     */
    String cart();
}
