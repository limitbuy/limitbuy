package com.limitbuy.entity;

/**
 * Created by chenjie on 15/10/30.
 */
public class Goods {

    private long productId;//商品Id

    private long count;//商品数量

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
