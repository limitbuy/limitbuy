package com.limitbuy.controller;

import com.limitbuy.dao.LockCache;
import com.limitbuy.dao.RedisCacheDao;
import com.limitbuy.entity.Cart;
import com.limitbuy.entity.Goods;
import com.limitbuy.iface.CartServie;
import com.limitbuy.iface.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenjie on 15/10/30.
 */
@RestController
@RequestMapping(value = "/cart")
public class CartAction {

    private static final Logger log = LoggerFactory.getLogger(CartAction.class);
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
    public String insertCart(@RequestBody Cart cart) {
        StringBuilder result = new StringBuilder();
        if (redisCacheDao.isExistsUser(cart.getUserName())) {
            List<Goods> list = cart.getProductList();
            for (Goods g : list) {
                long count = g.getCount();
                Integer productId = g.getProductId();
                //获取锁
                try {
                    if (lockCache.getLock(Integer.toString(productId))) {
                        //1 先查询货物库存是否够
                        int stock = productService.queryGoodsCount(productId.toString());
                        if (stock < count) {
                            result.append("商品ID:"+productId + "库存不足,添加购物车失败\n");
                            continue;
                        }
                        //从库存里减去购买商品的数量xxxxxxx
                        productService.decreaseProduct(g);
                        //添加到购物车
                        cartServie.addToCart(cart.getUserName(), g);
                        redisCacheDao.delKey("lock:" + Integer.toString(productId));
                        result.append("商品ID:"+productId + "添加购物车成功\n");
                    }else{
                        result.append("抱歉,你没有抢到,商品ID:" + productId);
//                        redisCacheDao.expire("lock:" + Integer.toString(productId),1);
                    }
                } catch (InterruptedException e) {
                    log.error("error:{}", e.toString());
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
