package cn.lrs;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

//使用httpClient实现网络爬虫的客户端
public class HttpClientTest {
    @Test
    public void testGet() throws Exception {
        //1.创建HttpClient对象
//        DefaultHttpClient httpClient = new DefaultHttpClient();
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2.创建HttpGet请求并进行相关设置
        HttpGet httpGet = new HttpGet("https://www.itcast.cn/?username==xxx");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");

        //3.发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //4.判断响应状态码并获取响应数据
        if(response.getStatusLine().getStatusCode() == 200){//200表示响应成功
            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(html);
        }

        //5.关闭资源
        httpClient.close();
        response.close();
    }

    @Test
    public void testPost() throws Exception {
        //1.创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2.创建HttpPost对象并进行相关设置
        HttpPost httpPost = new HttpPost("https://www.itcast.cn/");
        //准备集合用来存放请求参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username","java"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
        httpPost.setEntity(entity);//设置请求体/参数
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");

        //3.发起请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //4.判断响应状态码并获取数据
        if (response.getStatusLine().getStatusCode() == 200){
            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(html);
        }

        //5.关闭资源
        response.close();
        httpClient.close();
    }

    @Test
    public void testPool() throws IOException {
        //1.创建HttpClient连接管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //2.设置参数
        cm.setMaxTotal(200);//设置最大连接数
        cm.setDefaultMaxPerRoute(20);//设置每个主机的最大并发
        doGet(cm);
        doGet(cm);

    }

    private void doGet(PoolingHttpClientConnectionManager cm) throws IOException {
        //3.从连接池中获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //在这里加上断点观察到，每次从池中获取到一个HttpClient对象都是不同的
        //4.创建HttpGet对象
        HttpGet httpGet = new HttpGet("https://www.itcast.cn/");
        //5.发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //6.获取数据
        if(response.getStatusLine().getStatusCode() == 200){
            String html  = EntityUtils.toString(response.getEntity(), "UTF-8");
        }

        //7.关闭资源
        response.close();
//        httpClient.close();//注意这里不要关闭HttpClient对象，因为使用连接池，HTTPClient对象使用完之后应该还回到池中，而不是关掉
    }

    @Test
    public void testConfig() throws IOException {
        //0.创建请求配置对象
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)//设置连接超时时间
                .setConnectTimeout(100000)//设置创建连接超时时间
                .setConnectionRequestTimeout(10000)//设置请求超时时间
                .setProxy(new HttpHost("125.46.0.62",53281))//添加代理
                .build();
        //1.创建httpClient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        //2.创建HttpGet对象
        HttpGet httpGet = new HttpGet("https://www.itcast.cn/");
        //3.发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //4.获取响应数据
        if(response.getStatusLine().getStatusCode() == 200){
            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(html);
        }

        //5.关闭资源
        response.close();
        httpClient.close();

    }

}
