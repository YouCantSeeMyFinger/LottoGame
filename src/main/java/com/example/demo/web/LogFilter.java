package com.example.demo.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter init :: invoked");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter :: invoked");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String requestURI = httpServletRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("RequestURI => {} , UUID => {}", requestURI, uuid);
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            log.info("RequestURI => {} , UUID => {}", requestURI, uuid);
        }
    }

    @Override
    public void destroy() {
        log.info("Filter destroy :: invoked");
    }
}
