package com.blog.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Session超时，通知前端删除token
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String SESSION_TIME_OUT_K = "SESSION_TIME_OUT";
    private static final String SESSION_TIME_OUT_V = "timeout";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //判断session
        String uri = request.getRequestURI();
        if(uri.startsWith("/**") && request.getSession().getAttribute("loginUser") == null){
            //将结果放入response头
            if (request.getHeader("Origin") != null) {
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            }
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            //登陆时效返回状态码401
            response.setStatus(401);

            return false;
        }else {
            return true;
        }
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
