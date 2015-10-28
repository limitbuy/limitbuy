package com.eleme.limitbuy.entity;

/**
 * Created by longwu on 15/10/28.
 */
public class Product extends BaseModel{
    private long id;
    private String productId;//商品目录ID
    private String name;//商品名称
    private String price;//定价
    private String nowPrice;//现价
    private int stock;//库存
    private int status;//商品状态。1：新增，2：已上架，3：已下架

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
