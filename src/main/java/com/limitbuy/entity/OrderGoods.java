package com.limitbuy.entity;

/**
 * Created by chenjie on 15/11/4.
 */
public class OrderGoods {

    private int orderId;

    private int productId;

    private int count;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderGoods{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", count=" + count +
                '}';
    }
}
