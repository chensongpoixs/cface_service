/*
 * Copyright (c) 2020 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.exadel.frs.system.security.config;

import static com.exadel.frs.system.global.Constants.ADMIN;

import com.exadel.frs.config.AnonymousAuthenticationEntryPoint;
import com.exadel.frs.system.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AnonymousAuthenticationEntryPoint anonymousAuthenticationEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, ADMIN + "/oauth/**").permitAll()
//                .antMatchers("/actuator/**", ADMIN + "/user/register", ADMIN + "/user/forgot-password", ADMIN + "/user/reset-password", ADMIN + "/config",
//                        "/api/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .permitAll();
//        http.cors().and().csrf().disable();
//        // 设置允许添加静态文件
//        http.headers().contentTypeOptions().disable();
        http.cors().and().csrf().disable();
        // 设置允许添加静态文件
        http.headers().contentTypeOptions().disable();
        http.authorizeRequests()
                // 放行接口
                .antMatchers("/actuator/**", ADMIN + "/user/register", ADMIN + "/user/forgot-password", ADMIN + "/user/reset-password", ADMIN + "/config",
                     "/api/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, ADMIN + "/oauth/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                // 异常处理(权限拒绝、登录失效等)
                .and().exceptionHandling()
                //匿名用户访问无权限资源时的异常处理
                .authenticationEntryPoint(anonymousAuthenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler)//登录用户没有权限访问资源
                // 登入 允许所有用户
                .and().formLogin().permitAll()
                //登录成功处理逻辑
//                .successHandler(loginSuccessHandler)
//                登录失败处理逻辑
//                .failureHandler(loginFailureHandler)
                // 登出
                .and().logout().logoutUrl("/api/user/logout").permitAll()
                //登出成功处理逻辑
//                .logoutSuccessHandler(logoutHandler)
                .deleteCookies("JSESSIONID")
        // 会话管理
//                .and().sessionManagement().invalidSessionStrategy(invalidSessionHandler) // 超时处理
//                .maximumSessions(1)//同一账号同时登录最大用户数
//                .expiredSessionStrategy(sessionInformationExpiredHandler) // 顶号处理
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
                "/swagger-ui**", "/webjars/**", "/lms/**"
        );
    }
}
