package com.limitbuy.controller;

import com.limitbuy.dao.LockCache;
import com.limitbuy.dao.RedisCacheDao;
import com.limitbuy.entity.Cart;
import com.limitbuy.entity.Goods;
import com.limitbuy.iface.CartServie;
import com.limitbuy.iface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjie on 15/10/30.
 */
@RestController
@RequestMapping(value = "/cart")
public class CartAction {

    @Autowired
    public CartServie cartServie;
    @Autowired
    private ProductService productService;
    @Autowired
    private RedisCacheDao redisCacheDao;
    @Autowired
    private LockCache lockCache;

    /**
     * 添加购物车
     * @param cart
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/insertCart", method = RequestMethod.POST)
    public String insertCart(@RequestBody Cart cart) throws InterruptedException {
        StringBuilder result = new StringBuilder();
        if (redisCacheDao.isExistsUser(cart.getUserName())) {
            List<Goods> list = cart.getProductList();
            for (Goods g : list) {
                long count = g.getCount();
                Integer productId = g.getProductId();
                //获取锁
                if (lockCache.getLock(Integer.toString(productId))) {
                    //1 先查询货物库存是否够
                    int stock = productService.queryGoodsCount(productId.toString());
                    if (stock < count) {
                        result.append("商品ID:"+productId + "库存不足,添加购物车失败\n");
                        continue;
                    }
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("userName", cart.getUserName());
                    map.put("count", g.getCount());
                    map.put("productId", g.getProductId());
                    //从库存里减去购买商品的数量
                    productService.decreaseProduct(map);
                    //添加到购物车
                    cartServie.addToCart(map);
                    result.append("商品ID:"+productId + "添加购物车成功\n");
                }
            }
        } else {
             result.append("该用户没有登录,请先登录!");
        }
        return result.toString();
    }

    @RequestMapping(value = "/cancleCart", method = RequestMethod.GET)
    public String canceleCart(String userName) {
        System.out.print(userName);
        cartServie.deleteFromCart(userName);
        return userName;

    }
}
