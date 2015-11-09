package com.limitbuy.listener;

import com.limitbuy.dao.RedisCacheDao;
import com.limitbuy.entity.Product;
import com.limitbuy.iface.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.List;

/**
 * Created by longwu on 15/11/9.
 */
public class LoadListener implements InitializingBean{

    @Autowired
    private ProductService productService;
    @Autowired
    private RedisCacheDao redisCacheDao;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setRedisCacheDao(RedisCacheDao redisCacheDao) {
        this.redisCacheDao = redisCacheDao;
    }


    public void afterPropertiesSet() throws Exception {
        List<Product> products = productService.queryAllGoods();
        for (Product product : products){
            redisCacheDao.setGoodsStock(String.valueOf(product.getId()), String.valueOf(product.getStock()));
        }
    }
}
