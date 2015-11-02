package com.limitbuy.iface.impl;

import com.limitbuy.dao.ProductDao;
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

    public int insertProduct(Product product) {
        return productDao.insertProduct(product);
    }

    public int decreaseProduct(Map map) {
        return productDao.decreaseProduct(map);
    }

    public int checkGoods(Map map) {
        return productDao.checkGoods(map);
    }
}
