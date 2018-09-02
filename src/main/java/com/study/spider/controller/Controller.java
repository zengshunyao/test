package com.study.spider.controller;

import com.alibaba.fastjson.JSON;
import com.study.spider.model.IndustrialCrawler;
import com.study.spider.model.SupplyAndSaleCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.util.concurrent.CountDownLatch;

public class Controller {
    public static CountDownLatch countDownLatch = new CountDownLatch(1834);

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "E:\\logs\\spider";
        int numberOfCrawlers = 13;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        //专家服务
//        for (int i = 0; i <= 1834; i++) {
//            controller.addSeed("http://www.sckjfp.com/Index/userInfoList/p/" + i + ".html");
//        }
        //技术供给
//        for (int i = 0; i <= 129; i++) {
//            controller.addSeed("http://www.sckjfp.com/Index/techSupplySearch/p/" + i + ".html");
//        }
        //产业信息
//        for (int i = 0; i <= 49; i++) {
////            controller.addSeed("http://www.sckjfp.com/Index/industryList/p/" + i + ".html");
////        }
        //供销对接
        for (int i = 0; i <= 52; i++) {
            controller.addSeed("http://www.sckjfp.com/Index/e_commerceList/p/" + i + ".html");
        }

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(SupplyAndSaleCrawler.class, numberOfCrawlers);
//        countDownLatch.await();
        System.out.println(JSON.toJSONString(SupplyAndSaleCrawler.completedSet.toArray()));
        System.out.println("<---------------------------------------->");
        //Arrays.toString
        System.out.println(JSON.toJSONString(SupplyAndSaleCrawler.industrials.toArray()));

    }
}
