package com.eleme.limitbuy.iface;

/**
 * Created by longwu on 15/10/28.
 */
public interface CartInterface {

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    String addToCart(Cart cart);

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
