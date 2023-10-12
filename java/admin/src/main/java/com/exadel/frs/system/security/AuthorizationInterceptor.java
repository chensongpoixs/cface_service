//package com.exadel.frs.system.security;
//
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class AuthorizationInterceptor implements HandlerInterceptor
//{
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//    {
//        IgnoreAuth annotation;
//        if (handler instanceof HandlerMethod)
//        {
//
//            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
//        } else {
//            return true;
//        }
//        // 如果有@IgnoreAuth注解，则不验证token
//        if (annotation != null)
//        {
//            return true;
//        }
//        return true;
//    }
//}
