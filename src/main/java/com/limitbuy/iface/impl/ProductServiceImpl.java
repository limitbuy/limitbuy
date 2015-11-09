package com.limitbuy.iface.impl;

import com.limitbuy.dao.LockCache;
import com.limitbuy.dao.ProductDao;
import com.limitbuy.dao.RedisCacheDao;
import com.limitbuy.entity.Goods;
import com.limitbuy.entity.Product;
import com.limitbuy.iface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenjie on 15/11/2.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
    private ProductDao productDao;

    @Autowired
    private LockCache lockCache;
    @Autowired
    private RedisCacheDao redisCacheDao;

    public int insertProduct(Product product) {
        return productDao.insertProduct(product);
    }

    public int decreaseProduct(final Goods goods) {
        executorService.submit(new Runnable() {
            public void run() {
                productDao.decreaseProduct(goods);
            }
        });
        Integer productId = goods.getProductId();
        Integer count = goods.getCount();
        Integer goodsCount = redisCacheDao.queryGoodsStock(productId.toString());
        Integer num = goodsCount - count;
        redisCacheDao.setGoodsStock(productId.toString(), num.toString());
        return 1;
    }

    public int checkGoods(Map map) {
        return productDao.checkGoods(map);
    }

    public int queryGoodsCount(String productId) {
        Integer goodsCount = redisCacheDao.queryGoodsStock(productId);
        if(goodsCount >= 0){
            return goodsCount;
        }else{
            goodsCount = productDao.queryGoodsCount(Integer.parseInt(productId));
            redisCacheDao.setGoodsStock(productId, goodsCount.toString());
        }
        return goodsCount;
    }

    public List<Product> queryAllGoods() {
        return productDao.queryAllGoods();
    }
}
