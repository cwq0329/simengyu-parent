package com.cwq.core.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cwq.core.service.SessionService;
import com.cwq.core.tools.SessionTools;

/**
 * 自定义spring mvc 拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
       //判断登录
        String username = sessionService.getUsernameFromRedis(SessionTools.getSessionId(request, response));
        if(username==null){
            //返回登录页面，登陆后进入购物车页面
            response.sendRedirect("http://localhost:8081/login.aspx?returnUrl=http://localhost:8082/cart");
            return false;
        }else{
            //放行
            return true;
        }
        
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // TODO Auto-generated method stub

    }

}
