package com.limitbuy.dao;

import java.util.Map;

/**
 * Created by chenjie on 15/10/30.
 */

public interface GoodsDao {

    public int insertCart(Map map);

    public  int cancleCart(String userName);
}
