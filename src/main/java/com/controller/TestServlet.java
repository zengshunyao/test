package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Lenovo on 2015/5/31.
 */

/**
 * demo
 * 路径参数demo
 */
public class TestServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        System.out.println("fdffffffff");
        System.out.println(request.getParameter("name").toString());
        System.out.println(System.getProperty("user.dir"));
        response.sendRedirect("index.jsp");
    }

    @ResponseBody
    @RequestMapping(params = "method=queryPage4Ajax", method = {RequestMethod.POST}, produces = {"text/html;charset=utf-8"})
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

//    @RequestMapping("/test/{name}")
//    public String test(@PathVariable String n, @RequestParam(value = "name") String name, @("f") File[] files) {
//        return null;
//    }

//    @RequestMapping(value = "/ok{textualPart:[a-z-]+}.{numericPart:[\\d]+}")
//    public String regularExpression(
//            @PathVariable String textualPart,
//            @PathVariable String numericPart) {
//
//        System.out.println("Textual part: " + textualPart +
//                ", numeric part: " + numericPart);
//        return "someResult";
//    }
}
