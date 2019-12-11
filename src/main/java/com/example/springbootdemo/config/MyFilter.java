package com.example.springbootdemo.config;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest srequest = (HttpServletRequest)servletRequest;
        System.out.println("this is my request url: "+ srequest.getRequestURI());
        filterChain.doFilter(srequest,servletResponse);
    }
}