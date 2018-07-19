package com.funi.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Download {

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
//            String url = "http://www.funi.com/loupan/region_0_0_0_0_1;jsessionid=0d8osnnLetLhpE3U57CrHRAW.undefined";
            String url = "https://www.baidu.com";
            Document document = Jsoup.connect(url).get();
            System.out.println(document.outerHtml());
            long endTime = System.currentTimeMillis();
            System.out.println("耗时："+(endTime - startTime)+"ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
