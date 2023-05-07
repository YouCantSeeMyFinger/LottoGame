package com.example.demo.webconfig;


import com.example.demo.web.LogFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    /**
     * HTTP request => WAS => Filter1 => Filter2 ... FilterN => Servlet => Controller
     *
     * @return filterBean
     */

    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new LogFilter());
        filterBean.setOrder(1);
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }
}
