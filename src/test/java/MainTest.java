import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.limitbuy.entity.*;
import com.limitbuy.iface.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by longwu on 15/10/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring.xml")
public class MainTest {
    private static Logger log = LoggerFactory.getLogger(MainTest.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CartServie cartServie;
    @Autowired
    private ProductService productService;
    @Autowired
    protected RushService rushService;

    @Autowired
    private OrderService orderService;


    @Ignore
    public void testRegist() {
        User user = new User();
        user.setUsername("xlw1");
        user.setCreateTime(new Date());
        user.setCreateBy("xlw");
        String result = userService.register(user);
        System.out.println(result);
    }

    @Ignore
    public void testLogin() {
        String username = "xlw";
        String password = "xlw";
        String login = userService.login(username);
        log.info("*************" + login + "*************");
    }

    @Ignore
    public void testInsertCart() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", "chenjie");
        Goods goods = new Goods();
        goods.setProductId(17);
        goods.setCount(2);
        map.put("goods",goods);
/*
        map.put("productId", 17);
        map.put("count", 7);*/
        cartServie.addToCart(map);

    }

    @Ignore
    public void testInertC() {

        Goods g = new Goods();
        g.setCount(3);
        g.setProductId(1);
        List<Goods> list = new ArrayList<Goods>();
        list.add(g);
        Cart c = new Cart();
        c.setUserName("chenjie");
        c.setProductList(list);
        c.setAmount("890.09");
        String s = JSONArray.toJSONString(c);
        System.out.print(s);

    }

    @Ignore
    public void testProductI() {
        Product p = new Product();
        p.setName("iphone6s");
        p.setPrice(12.34);
        p.setStock(99);
        productService.insertProduct(p);
    }

    @Ignore
    public void testProdecuIncrease() {

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("productId", 2);
        map.put("count", 9);
        productService.decreaseProduct(map);


    }

    @Test
    public void testCheck() {
      Map<String,Object> map = new HashMap<String, Object>();
        map.put("productId",1);
        map.put("count",500);
        System.out.print("+++++++++++++++++" + productService.checkGoods(map));
    }

    @Test
    public void testQueryGoods(){


        System.out.print("+++++++++++++++++"+productService.queryGoodsCount("3"));

    }

    @Test
    public void testOrderinsert(){


        Order o = new Order();
        o.setUserName("chenjie");
        orderService.insert(o);
        System.out.print("++++++++++++++++++++++++++++++++"+o.getId());
    }


    @Test
    public  void  testOrderquery(){

        String userName = "chenjie";
        orderService.queryOrder("");
    }

    @Test
    public  void  testOrderqueryAll(){

        StringBuilder sb = new StringBuilder("系统所有订单: ");
        List<Order> orders = orderService.queryAll();

        for (Order order : orders){
            sb.append("\n用户名:"+ order.getUserName());
            int i = 0;
            for(Goods goods : order.getProductList()){
                sb.append(" ,订单" + (++i) + ": 商品id: " + goods.getProductId() + " ,商品数量: " + goods.getCount());
            }
        }


        System.out.println(sb);
    }



}
