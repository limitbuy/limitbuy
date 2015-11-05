import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.limitbuy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by longwu on 15/10/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring.xml")
public class RestfulTest {

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
