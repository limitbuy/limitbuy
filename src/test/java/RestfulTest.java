import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.limitbuy.entity.Cart;
import com.limitbuy.entity.Goods;
import com.limitbuy.entity.Product;
import com.limitbuy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by longwu on 15/10/30.
 */
public class RestfulTest {


    private static final Logger log = LoggerFactory.getLogger(RestfulTest.class);
   static ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++){
            executorService.submit(new Runnable() {
                public void run() {
                    String username = Thread.currentThread().getName();
                    String login = login(username);
                    log.info("线程名:{},登陆结果:{}",Thread.currentThread().getName(),login);
                    String queryAll = queryAll();
//                    log.info("食物列表:{}", queryAll);
                    List<Product> products = JSONObject.parseArray(queryAll, Product.class);
                    List<Goods> goodsList = new ArrayList<Goods>();
                    int r = new Random().nextInt(3) + 1;
                    for (int i = 0; i < r; i++){
                        Product product = products.get(new Random().nextInt(products.size()));
                        Goods goods = new Goods();
                        goods.setCount(4);
                        goods.setProductId(product.getId());
                        goodsList.add(goods);
                    }
                    Cart cart = new Cart();
                    cart.setProductList(goodsList);
                    cart.setUserName(username);
                    String addcart = addCart(cart);
                    log.info("添加购物车:{}",addcart);
                    String buy = buy(username);
                    log.info("下单:{}",buy);

                }
            });
            log.info("*****************{}******************",i);
        }
    }



    public static String login(String username){
        User user = new User();
        user.setUsername(username);
        String url = "http://localhost:8080/limitbuy/user/login";
        String json = JSONObject.toJSONString(user);
        String httpPost = HttpUtils.httpPost(url, json);
        return httpPost;
    }

    public static String queryAll(){
        String url = "http://localhost:8080/limitbuy/product/query/all";
        String httpPost = HttpUtils.httpGet(url);
        return httpPost;
    }

    private static String addCart(Cart cart){
        String url = "http://localhost:8080/limitbuy/cart/insertCart";
        String json = JSONObject.toJSONString(cart);
        String httpPost = HttpUtils.httpPost(url, json);
        return httpPost;
    }

    private static String buy(String username){
        String url = "http://localhost:8080/limitbuy/order/buy/" + username;
        String httpPost = HttpUtils.httpGet(url);
        return httpPost;
    }


    @Test
    public void testRegist(){
        User user = new User();
        user.setUsername("xlw13");
        String url = "http://localhost:8080/limitbuy/user/regist";
        String json = JSONObject.toJSONString(user);
        String httpPost = HttpUtils.httpPost(url, json);

        System.out.println(httpPost);
    }

    @Test
    public void testLogin(){
        User user = new User();
        user.setUsername("xlw124");

        String url = "http://localhost:8080/limitbuy/user/login";
        String json = JSONObject.toJSONString(user);
        String httpPost = HttpUtils.httpPost(url, json);
        //        JSONObject jsonObject = new JSONObject("");

//        String httpPost = HttpUtils.httpPost(url, jsonObject);

        System.out.println(httpPost);
    }

    @Test
    public void testQueryStock(){

        String url = "http://localhost:8080/limitbuy/product/query/1";
        String httpPost = HttpUtils.httpGet(url);
        //        JSONObject jsonObject = new JSONObject("");

//        String httpPost = HttpUtils.httpPost(url, jsonObject);

        System.out.println(httpPost);
    }

}
