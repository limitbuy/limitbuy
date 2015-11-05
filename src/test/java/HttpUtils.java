import com.alibaba.fastjson.JSONObject;
import com.limitbuy.entity.Cart;
import com.limitbuy.entity.Goods;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by longwu on 15/10/29.
 */
public class HttpUtils {
    public static String httpGet(String url){
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(
                    url);
            getRequest.addHeader("accept", "application/json");

            HttpContext httpContext = new BasicHttpContext();

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            StringBuffer result = new StringBuffer();
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                result.append(output);
            }

            httpClient.getConnectionManager().shutdown();
            return result.toString();

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;

    }

    public static String httpPost(String url,String json){
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(
                    url);

            StringEntity input = new StringEntity(json,"utf-8");
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            HttpEntity entity = response.getEntity();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(entity.getContent(),"UTF-8"));

            String output;
            StringBuilder result = new StringBuilder();
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                result.append(output);
            }

            httpClient.getConnectionManager().shutdown();
            return result.toString();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }


    public static void main(String[] args) {
        Cart cart = new Cart();
        Goods goods = new Goods();
        goods.setProductId(1);
        goods.setCount(32);
        Goods goods1 = new Goods();
        goods1.setProductId(2);
        goods1.setCount(32);
        List<Goods> goodsList = new ArrayList<Goods>();
        goodsList.add(goods);
        goodsList.add(goods1);
        cart.setProductList(goodsList);
        cart.setUserName("xlw");
        System.out.println(JSONObject.toJSONString(cart));
    }

}
