package com.limitbuy.iface.impl;

import com.limitbuy.dao.LockCache;
import com.limitbuy.dao.ProductDao;
import com.limitbuy.dao.RedisCacheDao;
import com.limitbuy.entity.Product;
import com.limitbuy.iface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by chenjie on 15/11/2.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private LockCache lockCache;
    @Autowired
    private RedisCacheDao redisCacheDao;

    public int insertProduct(Product product) {
        return productDao.insertProduct(product);
    }

    public int decreaseProduct(Map map) {
        int result =  productDao.decreaseProduct(map);
        String productId = map.get("productId").toString();
        Integer count = Integer.parseInt(map.get("count").toString());
        Integer goodsCount = redisCacheDao.queryGoodsCount(productId);
        Integer num = goodsCount - count;
        redisCacheDao.setGoodsCount(productId,num.toString());
        return result;
    }

    public int checkGoods(Map map) {
        return productDao.checkGoods(map);
    }

    public int queryGoodsCount(String productId) {
        Integer goodsCount = redisCacheDao.queryGoodsCount(productId);
        if(goodsCount > 0){
            return goodsCount;
        }else{
            goodsCount = productDao.queryGoodsCount(Integer.parseInt(productId));
            redisCacheDao.setGoodsCount(productId,goodsCount.toString());
        }
        return goodsCount;
    }
}
