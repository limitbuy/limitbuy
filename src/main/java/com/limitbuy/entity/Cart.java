package com.limitbuy.entity;

import java.util.List;

/**
 * Created by longwu on 15/10/28.
 */
public class Cart extends BaseModel{
    //te
    private List<Product> productList;//购物车中商品列表
    private String amount;//合计总金额，也就是用户最终需要支付的金额

    public List<Product> getProducetList() {
        return productList;
    }

    public void setProducetList(List<Product> productList) {
        this.productList = productList;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productList=" + productList +
                ", amount='" + amount + '\'' +
                '}';
    }
}
