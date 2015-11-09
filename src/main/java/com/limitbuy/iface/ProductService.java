package com.limitbuy.iface;

import com.limitbuy.entity.Goods;
import com.limitbuy.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by chenjie on 15/11/2.
 */
public interface ProductService {

    public  int insertProduct(Product product);

    public  int decreaseProduct(Goods goods);

    public  int checkGoods(Map map);

    public  int queryGoodsCount(String productId);
    public List<Product> queryAllGoods();

}
