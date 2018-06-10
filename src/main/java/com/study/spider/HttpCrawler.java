package com.study.spider;

/**
 * Created by Lenovo on 2015/6/12.
 */

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class HttpCrawler {
    public static void main(String[] args) {

        String content = null;
        try {
            HttpClient httpClient = new HttpClient();
            //1、网络请求
            GetMethod method = new GetMethod("http://www.baidu.com");
            int statusCode = httpClient.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {
                content = method.getResponseBodyAsString();
                //结构化扣取
                String title = StringUtils.substringBetween(content, "<title>", "</title>");
                //存储
                System.out.println(title);
            }

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}