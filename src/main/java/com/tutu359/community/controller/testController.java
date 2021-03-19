package com.tutu359.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class testController {

    @RequestMapping("/loginTest")
    public void fun1(HttpServletRequest request, HttpSession session, HttpServletResponse response){

        System.out.println("\n请求方式：");
        System.out.println(request.getMethod());

        System.out.println("\n请求地址：");
        System.out.println(request.getRequestURL());

        System.out.println("\n请求体参数：");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            System.out.println(name+" :"+request.getParameter(name));
        }

        System.out.println("\nURL参数：");
        String queryString = request.getQueryString();
        System.out.println(queryString);

        System.out.println("\n请求头：");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            System.out.println(name+" :"+request.getHeader(name));
        }


    }



}


