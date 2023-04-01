package com.lrs.generator;

import com.alibaba.fastjson.JSON;
import com.lrs.Bean.MateriaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author LRS
 * @Date 2022/6/28 11:06
 * Desc 使用程序模拟生成疫情数据
 */
@Component
public class Covid19DataGenerator {
    @Autowired
    private KafkaTemplate kafkaTemplate;

//    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 10)
    public void generator() {
        Random ran = new Random();
        for (int i = 0; i < 10; i++) {
            MateriaBean materiaBean = new MateriaBean(wzmc[ran.nextInt(wzmc.length)], wzly[ran.nextInt(wzly.length)], ran.nextInt(1000));
            System.out.println(materiaBean);
            //将生成的疫情物资数据转换为jsonStr再发送到Kafka集群
            String jsonStr = JSON.toJSONString(materiaBean);
            kafkaTemplate.send("covid19_wz", ran.nextInt(3), jsonStr);
        }
    }

    //物资名称
    private String[] wzmc = new String[]{"N95口罩/个", "医用外科口罩/个", "84消毒液/瓶", "电子体温计/个", "一次性手套/套", "护目镜/副", "医用防护服/套"};

    //物资来源
    private String[] wzly = new String[]{"采购", "下拨", "捐赠", "消耗", "需求"};

}
