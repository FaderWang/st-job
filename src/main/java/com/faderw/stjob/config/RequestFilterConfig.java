package com.faderw.stjob.config;

import com.faderw.stjob.model.spring.ReqTraceLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author FaderW
 * 2019/7/29
 */
@Configuration
public class RequestFilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ReqTraceLogFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        registrationBean.setName("reqTraceLogFilter");

        return registrationBean;
    }
}
