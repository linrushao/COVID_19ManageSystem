package cn.lrs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.jsoup.Jsoup.parse;

//使用jsoup进行页面解析
public class JsoupTest {
    @Test
    public void testDoument() throws Exception {
        Document doc1 = Jsoup.connect("https://www.itcast.cn/").get();
        Document doc2 = parse(new URL("https://www.itcast.cn/"), 1000);
//        Document doc3 = Jsoup.parse(new File("jsoup.html"), "UTF-8");
//        String htmlstr = FileUtils.readFileToString(new File("jsoup.html"), "UTF-8");
//        Document doc4 = Jsoup.parse(htmlstr);
        System.out.println(doc1);
        System.out.println(doc2);
        Element titleElement1= doc1.getElementsByTag("title").first();
        Element titleElement2 = doc1.getElementsByTag("title").first();
        String title1 = titleElement1.text();
        String title2 = titleElement2.text();
        System.out.println(title1);
        System.out.println(title2);
    }

    @Test
    public void testGetElement() throws Exception {
        Document doc = parse(new URL("https://www.itcast.cn/"), 1000);
        System.out.println(doc);
//        //根据id获取元素getElementById
//        Element element = doc.getElementById("loading");
//        String text = element.text();
//        System.out.println(text);

        //根据标签获取元素getElementsByTag
        Elements elements = doc.getElementsByTag("title");
        Element titleElement = elements.first();
        String title = titleElement.text();
        System.out.println(title);

        //根据class获取元素getElementByClass
        Element element2 = doc.getElementsByClass("box_hd").last();
        System.out.println(element2.text());

        //根据属性获取元素
        String booksdata = doc.getElementsByAttribute("booksdata").first().text();
        System.out.println(booksdata);
    }

    @Test
    public void testElementOperator() throws Exception {
        Document doc = parse(new URL("https://www.itcast.cn/"), 1000);
        Element element = doc.getElementsByAttributeValue("class", "innr").first();
        //获取元素中的id
        String id = element.id();
        System.out.println(id);

        //获取元素中classname
        String className = element.className();
        System.out.println(className);

        //获取元素中的属性值
        String id1 = element.attr("id");
        System.out.println(id1);

        //获取元素中所有的属性
        String attrs = element.attributes().toString();
        System.out.println(attrs);
        //获取元素中的文本内容
        String text = element.text();
        System.out.println(text);
    }

    @Test
    public void testSelect() throws Exception {
        Document doc = parse(new URL("https://www.itcast.cn/"), 1000);
        //根据标签名获取元素
        Elements spans = doc.select("span");
        for (Element span:spans) {
            System.out.println(span.text());
        }
        //根据id获取元素
        String text = doc.select("#loading").text();
        System.out.println(text);

        //根据class获取元素
        String text1 = doc.select(".box_hd").text();
        System.out.println(text1);

        //根据属性获取元素
        String text2 = doc.select("[booksdata]").text();
        System.out.println(text2);

        //根据属性值获取元素
        String text3 = doc.select("[class=booksdata]").text();
        System.out.println(text3);
    }

    @Test
    public void testSelect2() throws Exception {
        Document doc = parse(new URL("https://www.itcast.cn/"), 1000);
        //根据标签名+id组合选取元素
//        String text = doc.select("li#loading").text();
//        System.out.println(text);
        //根据标签名+class
        String text1 = doc.select("li.xq_hz").text();
        System.out.println(text1);
        //根据标签名+元素名
        String text2 = doc.select("li[subjectdata]").text();
        System.out.println(text2);
        //任意组合
        String text3 = doc.select("span[class].span1").text();
        System.out.println(text3);

        //查找某个元素下的直接子元素
        String text4 = doc.select(".inner > ul > li").text();
        System.out.println(text4);

        //查找某个元素下的所有子元素
        String text5 = doc.select(".inner li").text();
        System.out.println(text5);
        //查找某个元素下的所有直接子元素
        String text6 = doc.select(".inner > *").text();
        System.out.println(text6);


    }
}
