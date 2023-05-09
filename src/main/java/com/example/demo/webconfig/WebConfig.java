package com.example.demo.webconfig;


import com.example.demo.web.filter.LogFilter;
import com.example.demo.web.filter.LoginCheckFilter;
import com.example.demo.web.interceptor.LogInterceptor;
import com.example.demo.web.interceptor.LoginCheckInterceptor;
import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/member-login", "/", "/member-add", "/css/**", "/logout", "/error", "/*.ico");
    }

    /**
     * HTTP request => WAS => Filter1 => Filter2 ... FilterN => InterCeptor => Servlet => Controller
     *
     * @return filterBean
     */

    //    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new LogFilter());
        filterBean.setOrder(1);
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }

    //    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
