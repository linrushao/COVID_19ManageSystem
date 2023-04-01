package cn.lrs.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//封装HttpClient工具类，方便爬取网页内容
public abstract class httputils {
    private static PoolingHttpClientConnectionManager cm = null;//声明httpclient管理器对象（HttpClient连接池）
    private static RequestConfig config = null;
    private static List<String> userAgentlist = null;

    //静态代码块会在类被加载的时候执行
    static{
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        config = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
        userAgentlist = new ArrayList<String>();
        userAgentlist.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 OPR/26.0.1656.60");
        userAgentlist.add("Mozilla/5.0 (Windows NT 5.1; U; en; rv:1.8.1) Gecko/20061208 Firefox/2.0.0 Opera 9.50");
        userAgentlist.add("Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10");
        userAgentlist.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2");
        userAgentlist.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
        userAgentlist.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.133 Safari/534.16");
        userAgentlist.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 UBrowser/4.0.3214.0 Safari/537.36");
        userAgentlist.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; SE 2.X MetaSr 1.0)");
    }

    public static String getHtml(String url){
        //1.从连接池中获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //2.创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        //3.设置请求配置对象和请求头
        httpGet.setConfig(config);
        httpGet.setHeader("User-Agent",userAgentlist.get(new Random().nextInt(userAgentlist.size())));

        //4.发送请求
        CloseableHttpResponse response =  null;
        try {
            response = httpClient.execute(httpGet);
            //5.获取响应内容
            if(response.getStatusLine().getStatusCode() == 200){
                String  html = "";
                if(response.getEntity() != null){
                    html = EntityUtils.toString(response.getEntity(), "UTF-8");
                }
                return html;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
//                httpClient.close();//注意：这里的HttpClient是从cm(连接池)中获取的，不需要关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String html = httputils.getHtml("https://www.itcast.cn/");
        System.out.println(html);
    }
}
