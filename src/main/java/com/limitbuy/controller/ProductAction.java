package com.limitbuy.controller;

import com.alibaba.fastjson.JSONObject;
import com.limitbuy.entity.Product;
import com.limitbuy.entity.User;
import com.limitbuy.iface.ProductService;
import com.limitbuy.iface.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

/**
 * Created by longwu on 15/11/3.
 */
@RestController
@RequestMapping("/product")
public class ProductAction {
    @Autowired
    private ProductService productService;

    /**
     * 查询商品库存
     * @param productId
     * @return
     */
    @RequestMapping(value = "/query/{productId}",method = RequestMethod.GET)
    public String queryStock(@PathVariable String  productId){
        int stock =  productService.queryGoodsCount(productId);
        String result = "商品id:" + productId + ",商品库存:" + stock;
        return result;
    }

    /**
     * 添加食物
     * @param product
     * @return
     */
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public String addProduct(@RequestBody Product product){
       int success = productService.insertProduct(product);
        if(success > 0){
            return "添加食物成功!";
        }else{
            return "添加食物失败!";
        }

    }

}
