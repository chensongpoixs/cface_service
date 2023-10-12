//package com.exadel.frs.config;
//
//
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.web.cors.CorsConfiguration;
////import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
////import org.springframework.web.filter.CorsFilter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//@Slf4j
//public class CorsConfig extends WebMvcConfigurerAdapter {
//
//    // 当前跨域请求最大有效时长（单位s）
//    private static final long MAX_AGE = 24 * 60 * 60;
//
////    @Bean
////    public CorsFilter corsFilter() {
////        CorsConfiguration corsConfiguration = new CorsConfiguration();
////        corsConfiguration.addAllowedOrigin("*"); // 1、设置访问源地址
////        corsConfiguration.addAllowedHeader("*"); // 2、设置访问源请求头
////        corsConfiguration.addAllowedMethod("*"); // 3、设置访问源请求方法
////        corsConfiguration.setMaxAge(MAX_AGE);
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", corsConfiguration); // 4、对接口配置跨域设置
////        return new CorsFilter(source);
////    }
//@Override
//public void addCorsMappings(CorsRegistry registry) {
//    // 跨域路径
//    CorsRegistration cors =registry.addMapping("/**");
//    cors.allowedOriginPatterns("*");
//    // 可访问的外部域
////    cors.allowedOrigins("*");
//    // 支持跨域用户凭证
//    //cors.allowCredentials(true);
//    //cors.allowedOriginPatterns("*");
//    // 设置 header 能携带的信息
//    cors.allowedHeaders("*");
//
//    cors.allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS");
//    cors.allowCredentials(true).maxAge(3600);
//}
////public void addInterceptors(InterceptorRegistry registry) {
////    // 拦截所有请求
////    registry.addInterceptor(globalInterceptor()).addPathPatterns("/**");
////    log.info("系统拦截器初始化完成");
////}
////
////    @Bean
////    public GlobalInterceptor globalInterceptor() {
////        return new GlobalInterceptor();
////    }
////
////    // 拦截器跨域配置
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        // 跨域路径
////        CorsRegistration cors = registry.addMapping("/**");
////        // 可访问的外部域
////        cors.allowedOriginPatterns("*");
////        // 支持跨域用户凭证
////        //cors.allowCredentials(true);
////        //cors.allowedOriginPatterns("*");
////        // 设置 header 能携带的信息
////        cors.allowedHeaders("*");
////        // 支持跨域的请求方法
////        cors.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
////        // 设置跨域过期时间，单位为秒
////        cors.maxAge(3600);
////    }
//
//}
