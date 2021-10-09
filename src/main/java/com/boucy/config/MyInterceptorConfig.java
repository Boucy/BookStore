package com.boucy.config;

import com.boucy.interceptor.AdminInterceptor;
import com.boucy.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private UserInterceptor userInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/book/bookStoreHomePage",
                        "/book/bookSearchByKey",
                        "/book/rankingList",
                        "/book/bookSearchByTypeID",
                        "/user/jumpLogin",
                        "/user/loginCheck",
                        "/user/jumpRegister",
                        "/user/useridCheck",
                        "/file/**",
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/font/**",
                        "/login.html",
                        "/user/userRegister",
                        "/book/bookSearchByID",
                        "/book/showBookComments",
                        "http://127.0.0.1:8090/**");

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns(
                        "/manager/**" ,
                        "/book/showUpdateBookPage" ,
                        "/file/bookPhotoUpload" ,
                        "/file/bookUpload" ,
                        "/book/updateBook" ,
                        "/book/deleteBook" ,
                        "/book/addBook" ,
                        "/user/deleteUser");
    }


}
