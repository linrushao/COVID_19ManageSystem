package com.lrs.crawler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lrs.Bean.CovidBean;
import com.lrs.util.HttpUtils;
import com.lrs.util.TimeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 实现疫情数据的爬取
 */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = DatasourceApplication.class)
@Component
public class Covid19DataCrawler {
    @Autowired
    private KafkaTemplate kafkaTemplate;

//    @Test
//    public void testKafkaTemplate() throws InterruptedException {
//        kafkaTemplate.send("test", 1, "abc");
//        Thread.sleep(1000000);
//    }

    //后续需要将该方法改为定时任务，如每天8点定时爬取疫情数据
//    @Test
//    @Scheduled(cron="0 0 8 * * ?")//每天8点定时爬取
//    @Scheduled(cron="0/30 * * * * ?")//每隔五秒执行一次
    @Scheduled(initialDelay = 1000,fixedDelay = 1000*60*60*24)
    public void testCrawler() throws Exception {
        System.out.println("每隔5s执行一次");
        String datetime = TimeUtils.format(System.currentTimeMillis(), "yyyy-MM-dd");
        //1.爬取指定页面
        String html = HttpUtils.getHtml("https://ncov.dxy.cn/ncovh5/view/pneumonia");
//        System.out.println(html);

        //2.解析页面中的指定内容-即id为getAreaStat的标签中全国疫情数据

        Document doc = Jsoup.parse(html);
        String text = doc.select("script[id=getAreaStat]").toString();
//        System.out.println(text);

        //3.使用正则表达式获取json格式的疫情数据
        String pattern = "\\[(.*)\\]";//定义正则规则
        Pattern reg = Pattern.compile(pattern);//编译成正则对象
        Matcher matcher = reg.matcher(text);//去text中进行匹配
        String jsonStr = "";
        if (matcher.find()) {//如果text中的内容和正则规则匹配上就取出来赋值给jsonStr变量
            jsonStr = matcher.group(0);
//            System.out.println(jsonStr);
        } else {
            System.out.println("no match");
        }

        //对json数据进行更近一步的解析
        //4.将第一层json（省份数据）解析为JavaBean
        List<CovidBean> pCovidBeans = JSON.parseArray(jsonStr, CovidBean.class);
        for (CovidBean pBean : pCovidBeans) {//pBean为省份
//            System.out.println(pBean);
            //先设置时间字段
            pBean.setDatetime(datetime);
            //获取cities
            String citysStr = pBean.getCities();
            //5.将第二层json（城市数据）解析为JavaBean
            List<CovidBean> covidBeans = JSON.parseArray(citysStr, CovidBean.class);
            for (CovidBean bean : covidBeans) {//bean为城市
//                System.out.println(bean);
                bean.setDatetime(datetime);
                bean.setPid(pBean.getLocationId());//把省份的id作为城市的pid
                bean.setProvinceShortName(pBean.getProvinceShortName());
//                System.out.println(bean);
                //后续需要将城市疫情数据发送给Kafka
                //将javaBean转为jsonStr再发送给Kafka
                String beanStr = JSON.toJSONString(bean);
                kafkaTemplate.send("covid19", bean.getPid(), beanStr);
            }
            //6.获取第一层json（省份数据）中的每一天的统计数据
            String statisticsDataUrl = pBean.getStatisticsData();
            String statisticsDataStr = HttpUtils.getHtml(statisticsDataUrl);
            //获取statisticsDataStr中的data字段对应的数据
            JSONObject jsonObject = JSON.parseObject(statisticsDataStr);
            String dataStr = jsonObject.getString("data");
//            System.out.println(dataStr);
            //将爬取解析出来的每一天的数据设置回省份pBean中的statisticsData字段中（之前该字段只是一个URL）
            pBean.setStatisticsData(dataStr);
            pBean.setCities(null);
//            System.out.println(pBean);
            //后续需要将省份疫情数据发送给Kafka
            //将javaBean转为jsonStr再发送给Kafka
            String pBeanStr = JSON.toJSONString(pBean);
            kafkaTemplate.send("covid19", pBean.getLocationId(), pBeanStr);
        }
//        Thread.sleep(1000000);
    }
}
