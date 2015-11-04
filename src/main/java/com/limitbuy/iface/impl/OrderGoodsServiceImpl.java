package com.limitbuy.iface.impl;

import com.limitbuy.dao.OrderDao;
import com.limitbuy.entity.OrderGoods;
import com.limitbuy.iface.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenjie on 15/11/4.
 */
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Autowired
    protected OrderDao orderDao;
    public int insertOrderGoods(OrderGoods orderGoods) {

        return orderDao.insertOrderGoods(orderGoods);
    }
}
