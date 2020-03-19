package com.atguigu.crm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * TODO
 *
 * @ClassName: MyMvcConfig
 * @author: admin
 * @since: 2020/2/21 21:50
 */

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(MyMvcConfig.class);

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){

        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("production/login");
                registry.addViewController("/index").setViewName("production/login");
                registry.addViewController("/main.html").setViewName("production/dashboard");
                registry.addViewController("/role/list").setViewName("role/list");
                registry.addViewController("/chance/list").setViewName("chance/list");
                registry.addViewController("/plan/list").setViewName("plan/list");
                registry.addViewController("/customer/list").setViewName("customer/list");
                registry.addViewController("/drain/list").setViewName("drain/list");
                registry.addViewController("/error").setViewName("production/errortest");
            }
            /*//注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login"
                                ,"/asserts/**","/production/**","/vendors/**","/src/**","/docs/**","/build/**"
                                ,"/webjars/bootstrap/**");
            }*/
        };
        return configurer;
    }



}
