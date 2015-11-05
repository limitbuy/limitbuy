package com.limitbuy.entity;

import java.util.List;

/**
 * Created by longwu on 15/10/28.
 */
public class Order extends BaseModel {
    public static final String order_status_init_chinese = "已下单";
    public static final String order_status_cancel_chinese = "已取消";
    public static final String order_paystatus_n = "n";// 未支付
    public static final String order_paystatus_y = "y";// 全部支付

    private int id;
    private String userName;
    private String account;//订单付款账户
    private String status;//订单状态
    private String amount;//订单总金额
    private int quantity;//商品总数量
    private List<Goods> productList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        int quantity = 0;
        for(Goods goods : productList){
            quantity += goods.getCount();
        }
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Goods> getProductList() {
        return productList;
    }

    public void setProductList(List<Goods> productList) {
        this.productList = productList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
