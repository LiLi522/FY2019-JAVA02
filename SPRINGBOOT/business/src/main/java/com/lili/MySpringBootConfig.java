package com.lili;

import com.google.common.collect.Lists;
import com.lili.interceptors.AdminAuthroityInterceptor;
import com.lili.interceptors.CommonInterceptor;
import com.lili.interceptors.PortalAuthorityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootConfiguration
public class MySpringBootConfig implements WebMvcConfigurer {

    //拦截后台请求，验证用户是否登录
    @Autowired
    AdminAuthroityInterceptor adminAuthroityInterceptor;

    @Autowired
    PortalAuthorityInterceptor portalAuthorityInterceptor;

    @Autowired
    CommonInterceptor commonInterceptor;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(commonInterceptor).addPathPatterns("/**");

        registry.addInterceptor(adminAuthroityInterceptor)
                .addPathPatterns("/manage/**");

        List<String> addPatterns = Lists.newArrayList();
        addPatterns.add("/user/**");
        addPatterns.add("/cart/**");
        addPatterns.add("/order/**");
        addPatterns.add("/shipping/**");

        List<String> excludePathPatterns = Lists.newArrayList();
        excludePathPatterns.add("/user/register.do");
        excludePathPatterns.add("/user/login/**");
        excludePathPatterns.add("/user/forget_get_question");
        excludePathPatterns.add("/user/forget_check_answer.do");
        excludePathPatterns.add("/user/forget_reset_password.do");
        excludePathPatterns.add("/order/callback.do");



        registry.addInterceptor(portalAuthorityInterceptor)
                .addPathPatterns(addPatterns)
                .excludePathPatterns(excludePathPatterns);


    }
}
