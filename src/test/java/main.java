import com.google.gson.Gson;
import entity.Process;
import entity.RequestBody;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import util.HttpUtils;

import java.io.IOException;

public class main {
    @Test
    public  void test(){
//
//        Gson gson = new Gson();
//        Process p = new Process();
//        p.setError_handling("1");
//        p.setFolder_path_type("path");
//        p.setFolder_path_value("Document");
//        p.setEntry_name("51CTO下载-Java编程思想（第五版）.pdf");
//        p.setExt_id("51CTO下载-Java编程思想（第五版）_123");
//        p.setEntry_file_path("C:\\work\\tmpsample\\51CTO下载-Java编程思想（第五版）.pdf");
//
//        RequestBody requestBody = new RequestBody();
//        String[] s={"normal"};
//        requestBody.setUser_list(s);
//        requestBody.setPriority("normal");
//        requestBody.setProcess(p);
//        System.out.println(gson.toJson(requestBody));
//
//        String url = "127.0.0.1/test?page=%d";

//        HttpUtils.sendHttpPostRequest(url,gson.toJson(requestBody));

        //1.通过url构建httpGet或HttPost请求
        HttpPost methon = new HttpPost("http://127.0.0.1:8080/document/put");
       //2.
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response =  client.execute(methon);
            System.out.println("response:"+response.getStatusLine().getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }
}
