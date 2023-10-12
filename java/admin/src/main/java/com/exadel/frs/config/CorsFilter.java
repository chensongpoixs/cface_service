//package com.exadel.frs.config;
//
////import com.xxx.base.util.Utils;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@WebFilter(filterName = "CorsFilter")
//public class CorsFilter implements Filter {
////    @Override
////    public void init(FilterConfig filterConfig) throws ServletException {
////    }
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpServletRequest request = (HttpServletRequest) req;
////        System.out.println("###"+response  + "## " + request.toString());
////        response.setHeader("Access-Control-Allow-Origin", "*");
////        if(Utils.isEmptyTrim(request.getHeader("Origin"))){
////         response.setHeader("Access-Control-Allow-Origin", "*");
////        }else{
////            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
////        }
////        response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
////        response.setHeader("Access-Control-Allow-Headers", "*");
//////        response.setHeader("Access-Control-Expose-Headers", "*");
////        response.setHeader("Access-Control-Allow-Methods", "*");
////        response.setHeader("Access-Control-Max-Age", "3600");
////        //response.setHeader("Access-Control-Allow-Credentials", "true");
//////        if ("OPTIONS".equals(request.getMethod())) {
//////            response.setStatus(HttpServletResponse.SC_OK);
//////            return;
//////        }
////        chain.doFilter(request, response);
////        String curOrigin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin", "*");
////        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PATCH, DELETE, PUT");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        //Content-Type,Access-Token,Authorization,ybg,x_access_token
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Access-Token,Authorization,ybg,x_access_token");
////        response.setHeader("Access-Control-Allow-Origin","*");
////        System.out.println("====" + response.getHeader("Access-Control-Allow-Origin"));
////        response.setHeader("Access-Control-Allow-Credentials", "true");
////        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
////        response.setHeader("Access-Control-Max-Age", "3600");
////        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//        chain.doFilter(req, res);
//
//    }
//    @Override
//    public void init(FilterConfig filterConfig)
//    {
//        System.out.println("=======================================================chulaile=================================================");
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
//
