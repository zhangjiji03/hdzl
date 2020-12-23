package com.zz.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zz.result.Result;
import com.zz.result.ResultUtil;
import com.zz.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称:     hdzl
 * 类名称:       TokenInterceptor
 * 类描述:       java类作用描述
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/8 16:46
 * 版本:         1.0
 */
@Component
public class TokenInterceptor implements HandlerInterceptor{


    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        if (request.getRequestURI().contains("/login") || request.getRequestURI().contains("/register") || request.getRequestURI().contains("/error") || request.getRequestURI().contains("/static")) {
            return true;
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        // 获取在前端存储的token值。
        String token = request.getHeader("token");
        response.setCharacterEncoding("utf-8");
        // 跨域ajax请求，都会先发一次method为OPTIONS的预请求
        // 1、获取服务器支持的HTTP请求方法。
        // 2、用来检查服务器的性能。例如：AJAX进行跨域请求时的预检，需要向另外一个域名的资源发送一个HTTP OPTIONS请求头，用以判断实际发送的请求是否安全。
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        } else if (token != null) {
            // token不为空，验证是否token是否过期,再验证token是否合法
            if(jwtTokenUtil.isTokenExpired(token)){
                String username=jwtTokenUtil.getUsernameFromToken(token);
                if (StringUtils.isNotBlank(username)&&jwtTokenUtil.validateToken(token,username)) {
                    return true;
                }
            }
        }
        Result result = ResultUtil.error("用户过期，请重新登录");
        String json = JSONObject.toJSONString(result);
        response.getWriter().write(json);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
