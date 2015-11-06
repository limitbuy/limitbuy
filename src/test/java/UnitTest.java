import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.limitbuy.dao.LockCache;
import com.limitbuy.dao.RedisCacheDao;
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
public class UnitTest {
    private static Logger log = LoggerFactory.getLogger(UnitTest.class);

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
    @Autowired
    private RedisCacheDao redisCacheDao;
    @Autowired
    private LockCache lockCache;


    /**
     * 登录
     */
    @Test
    public void testLogin() {
        String username = "xlw";
        String login = userService.login(username);
        log.info("*************" + login + "*************");
    }


    /**
     * 查询库存
     */
    @Test
    public void testQueryGoods(){
        //货物id
        String productId = "3";
        int stock = productService.queryGoodsCount(productId);
        log.info("*************货物ID:{},库存:{}*************",productId,stock);
    }

    /**
     * 创建购物车
     */
    @Test
    public void testInsertCart() throws InterruptedException {

        Cart cart = new Cart();
        cart.setUserName("xlw");
        List<Goods> goodsList = new ArrayList<Goods>();
        Goods goods1 = new Goods();
        goods1.setProductId(1);
        goods1.setCount(20);
        goodsList.add(goods1);
        goods1 = new Goods();
        goods1.setProductId(2);
        goods1.setCount(20);
        goodsList.add(goods1);
        cart.setProductList(goodsList);

        StringBuilder result = new StringBuilder();
        if (redisCacheDao.isExistsUser(cart.getUserName())) {
            List<Goods> list = cart.getProductList();
            for (Goods g : list) {
                long count = g.getCount();
                Integer productId = g.getProductId();
                //获取锁
                if (lockCache.getLock(Integer.toString(productId))) {
                    //1 先查询货物库存是否够
                    int stock = productService.queryGoodsCount(productId.toString());
                    if (stock < count) {
                        result.append("商品ID:"+productId + "库存不足,添加购物车失败\n");
                        continue;
                    }
                    //从库存里减去购买商品的数量
                    productService.decreaseProduct(g);
                    //添加到购物车
                    cartServie.addToCart(cart.getUserName(),g);
                    result.append("商品ID:"+productId + "添加购物车成功\n");
                }
            }
        } else {
            result.append("该用户没有登录,请先登录!");
        }
       log.info("*************\n{}*************",result);
    }

    /**
     * 添加食物
     */
    @Test
    public void testProductI() {
        String result = null;
        Product p = new Product();
        p.setName("iphone6s");
        p.setPrice(12.34);
        p.setStock(99);

        int success = productService.insertProduct(p);
        if(success > 0){
            result = "添加食物成功!";
        }else{
            result = "添加食物失败!";
        }
        log.info("*************\n{}\n*************",result);
    }

    /**
     * 下单
     */
    @Test
    public void testOrderinsert(){
        String result = null;
        Order order = new Order();
        order.setUserName("xlw");
        if (redisCacheDao.isExistsUser(order.getUserName())) {
            orderService.insert(order.getUserName());
            result = "下单成功!";
        }else{
            result = "该用户没有登录,请先登录!";
        }
        log.info("*************\n{}\n*************", result);

    }

    /**
     * 查询已下订单
     */
    @Test
    public  void  testOrderquery(){
        String userName = "chenjie";
        Order orders = orderService.queryOrder(userName);
        log.info("*************\n{}\n*************", JSONObject.toJSONString(orders));
    }

    /**
     * 查询系统所有订单
     */
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
        log.info("*************\n" +  JSONObject.toJSONString(sb) + "\n*************");

    }






    @Test
    public void testRegist() {
        User user = new User();
        user.setUsername("xlw1");
        user.setCreateTime(new Date());
        user.setCreateBy("xlw");
        String result = userService.register(user);
        System.out.println(result);
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
    public void testProdecuIncrease() {

        Goods goods = new Goods();
        goods.setCount(6);
        goods.setProductId(3);
        productService.decreaseProduct(goods);


    }
    @Test
    public void testCheck() {
      Map<String,Object> map = new HashMap<String, Object>();
        map.put("productId", 1);
        map.put("count", 500);
        System.out.print("+++++++++++++++++" + productService.checkGoods(map));
    }

}
