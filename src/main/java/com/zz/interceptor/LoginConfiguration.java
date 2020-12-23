package com.zz.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目名称:     hdzl
 * 类名称:       LoginConfiguration
 * 类描述:       java类作用描述
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/8 16:53
 * 版本:         1.0
 */
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

    /**
     * @Function: 这个方法才能在拦截器中自动注入查询数据库的对象
     * @author: YangXueFeng
     * @Date: 2019/4/14 13:10
     */
    @Bean
    TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    /**
     * @Function: 配置生成器：添加一个拦截器，拦截路径为login以后的路径
     * @author: YangXueFeng
     * @Date: 2019/4/14 13:10
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/static");
    }

}

