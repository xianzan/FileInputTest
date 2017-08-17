package util;
import java.io.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
public class HttpUtils {

    private static final int HTTP_OK = 200;
    private static final String HTTP_METHOD_POST = "POST";


    /**
     * 发送各类HTTP请求
     *
     * @param request
     *            HTTP请求
     * @param method
     *            请求方式，用于log输出
     * @return 所得回复内容的json格式
     */
    private static String sendHttpRequest(HttpUriRequest request, String method) {

        String result = "";
        HttpClient client = HttpClientBuilder.create().build();
        InputStreamReader inputReader = null;
        try {

            //执行post请求
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HTTP_OK) {
                //获取服务器响应的内容
                inputReader = new InputStreamReader(response.getEntity().getContent(), "utf-8");
                BufferedReader in = new BufferedReader(inputReader);
                String line = "";
                while ((line = in.readLine()) != null) {
                    result += line;
                    System.out.println("请求结果："+result);
                }

            } else {

                System.out.println("访问异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputReader != null) {
                try {
                    inputReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 发送HTTP POST请求
     * @param url
     *            请求URL
     * @param body
     *            请求实体
     * @return 所得回复内容
     */
    public static String sendHttpPostRequest(String url, String body) {

        String jsonObj = null;
        String wechatUrl = String.format(url);
        //组装成HttpPost对象
        HttpPost request = new HttpPost(wechatUrl);
        try {
            //创建Post请求实体，将请求的json数据放在实体中
            StringEntity entity;
            entity = new StringEntity(body, "UTF-8");
            entity.setContentType("application/json");
            entity.setContentEncoding("UTF-8");
            request.setEntity(entity);
            //发送请求
            jsonObj = sendHttpRequest(request, HTTP_METHOD_POST);

           // JSONObject jsonObject = new JSONObject(jsonObj);
           // int errcode = Integer.parseInt(jsonObject.optString("errcode"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObj;
    }


}
