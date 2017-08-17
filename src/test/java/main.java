import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;

public class main {
    @Test
    public void test(){
        //1.通过url构建httpGet或HttPost请求
        HttpPost methon = new HttpPost("http://127.0.0.1:8080/document/put");
       //2.构建HttpClient
        HttpClient client = HttpClientBuilder.create().build();
        try {
            //3.执行请求
            HttpResponse response =  client.execute(methon);
            System.out.println("response:"+response.getStatusLine().getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }
}
