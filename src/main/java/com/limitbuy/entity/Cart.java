package com.limitbuy.entity;

import java.util.List;

/**
 * Created by longwu on 15/10/28.
 */
public class Cart extends BaseModel {
    //te
    private List<Goods> productList;//购物车中商品列表
    private String amount;//合计总金额，也就是用户最终需要支付的金额
    private String userName;//用户名

    public List<Goods> getProductList() {
        return productList;
    }

    public void setProductList(List<Goods> productList) {
        this.productList = productList;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productList=" + productList +
                ", amount='" + amount + '\'' +
                '}';
    }
}
