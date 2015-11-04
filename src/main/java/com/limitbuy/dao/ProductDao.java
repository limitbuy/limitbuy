package com.limitbuy.dao;

import com.limitbuy.entity.Product;

import java.util.Map;

/**
 * Created by chenjie on 15/11/2.
 */
public interface ProductDao {

    public  int insertProduct(Product product);

    public  int decreaseProduct(Map map);

    public  int checkGoods(Map map);

    public  int queryGoodsCount(int productId);
}