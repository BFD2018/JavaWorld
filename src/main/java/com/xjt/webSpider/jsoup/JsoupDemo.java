package com.xjt.webSpider.jsoup;

import cn.hutool.core.io.FileUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupDemo {
    public static void main(String[] args) throws IOException {
        //爬彼岸网首页轮播图
        String baseUrl = "http://www.netbian.com/";
        String filePath = "D://spiderTemp/";
        if(!FileUtil.exist(filePath)){
            new File(filePath).mkdir();
        }

        Document doc = Jsoup.connect(baseUrl)
                .userAgent("User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .referrer(baseUrl)
                .ignoreContentType(true)
                .get();

        Elements elements = doc.select("#mainShow .slide ul li");
        for (Element element : elements) {
            String imgSrc = element.select("a img").get(0).attr("src");
            String imgName = imgSrc.trim().substring(imgSrc.lastIndexOf("/")+1);
            System.out.println("----->download image:" + imgName);

            Connection.Response execute = Jsoup.connect(imgSrc)
                    .referrer(baseUrl)
                    .ignoreContentType(true)
                    .execute();

            FileUtil.writeBytes(execute.bodyAsBytes(),filePath+imgName);
        }
    }
}
