package com.exadel.frs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理匿名用户访问逻辑
 * @author lin
 */
@Slf4j
@Component
public class AnonymousAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("code", ErrorCode.ERROR401.getCode());
//        jsonObject.put("msg", ErrorCode.ERROR401.getMsg());
//        String logUri = "api/user/login";
//        if (request.getRequestURI().contains(logUri)){
//            jsonObject.put("msg", e.getMessage());
//        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        try {
//            response.getWriter().print(jsonObject.toJSONString());
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
    }
}