import com.limitbuy.entity.User;
import com.limitbuy.iface.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by longwu on 15/10/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring.xml")
public class MainTest {
    private static Logger log = LoggerFactory.getLogger(MainTest.class);

@Autowired
private UserService userService;

    @Test
    public void testRegist(){
        User user = new User();
        user.setUsername("xlw13");
        user.setPassword("xlw");
        user.setEmail("xlw@ele.me");
        user.setAddress("上海市");
        user.setPhoneNum("13245645678");
        user.setCreateTime(new Date());
        user.setCreateBy("xlw");
        String result = userService.register(user);
        System.out.println(result);
    }

    @Test
    public void testLogin(){
        String username = "xlw12";
        String password = "xlw2";
        String login = userService.login(username, password);
        boolean userinfo = userService.redis(username);
        System.out.println("userinfo:"+userinfo);
        log.info("*************" + login + "*************");
    }


}
