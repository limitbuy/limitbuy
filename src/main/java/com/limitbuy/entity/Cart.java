package com.limitbuy.entity;

import java.util.List;

/**
 * Created by longwu on 15/10/28.
 */
public class Cart extends BaseModel{
    private List<Product> productList;//购物车中商品列表
    private String amount;//合计总金额，也就是用户最终需要支付的金额
    private Address address;//客户配送信息
    private List<Address> addressList;//用户配送地址信息
    private long defaultAddessID;//用户的默认地址ID

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public long getDefaultAddessID() {
        return defaultAddessID;
    }

    public void setDefaultAddessID(long defaultAddessID) {
        this.defaultAddessID = defaultAddessID;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "producetList=" + productList +
                ", amount='" + amount + '\'' +
                ", address=" + address +
                ", addressList=" + addressList +
                '}';
    }
}
