package com.blog.config;

import com.blog.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class BlogWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/admin为前缀的url路径
        registry.addInterceptor(loginInterceptor).addPathPatterns("/api/**");
    }

    //全局设置跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置静态文件映射路径
        String rootPath = System.getProperty("user.dir");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:"+rootPath+"/src/main/resources/uploads/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:"+rootPath+"/src/main/resources/static/");
    }
}
