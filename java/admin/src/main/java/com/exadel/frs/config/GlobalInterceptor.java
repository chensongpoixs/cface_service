//package com.exadel.frs.config;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class GlobalInterceptor implements HandlerInterceptor {
//
//    /**
//     * 拦截所有请求，校验url和token
//     */
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //加上校验放行
//        if(request.getMethod().equals("OPTIONS")){
//            return true;
//        }else{
//            ///
//        }
//        return false;
//    }
//
//}
