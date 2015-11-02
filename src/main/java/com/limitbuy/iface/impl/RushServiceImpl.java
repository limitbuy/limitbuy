package com.limitbuy.iface.impl;

import com.limitbuy.dao.ProductDao;
import com.limitbuy.dao.RushDao;
import com.limitbuy.iface.RushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by chenjie on 15/11/2.
 */
@Service
public class RushServiceImpl implements RushService {

    @Autowired
    private RushDao rushDao;

    public int rushProduct(Map map) {
      return rushDao.rushProduct(map);
    }
}
