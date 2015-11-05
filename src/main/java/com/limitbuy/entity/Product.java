package com.limitbuy.entity;

/**
 * Created by longwu on 15/10/28.
 */
public class Product{
    private long id;
    private String productId;//商品目录ID
    private String name;//商品名称
    private double price;//定价
    private int stock;//库存
    private String description ;
//    private int status;//商品状态。1：新增，2：已上架，3：已下架?

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
