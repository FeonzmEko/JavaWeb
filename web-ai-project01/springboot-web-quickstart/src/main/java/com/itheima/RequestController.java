package com.itheima;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @RequestMapping("/request")
    public String request(HttpServletRequest request){
        //1.获取请求方式
        String method = request.getMethod();
        System.out.println("请求方式：" + method);
        int i = 1/0;
        //2.获取请求url地址
        String URL = request.getRequestURL().toString();
        System.out.println("请求url地址：" + URL);
        String requestURI = request.getRequestURI();
        System.out.println("请求uri地址：" + requestURI);
        //3.获取请求协议
        String protocol = request.getProtocol();
        System.out.println("请求协议：" + protocol);
        //4.获取请求参数 - name
        String name = request.getParameter("name");
        System.out.println("请求参数-name：" + name);
        //5.获取请求头 - Accept
        String accept = request.getHeader("Accept");
        System.out.println("请求头-Accept：" + accept);
        return "OK";
    }
}
