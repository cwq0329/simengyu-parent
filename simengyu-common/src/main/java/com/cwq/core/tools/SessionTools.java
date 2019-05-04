package com.cwq.core.tools;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 自定义session工具类，主要用来生成mysessionId及分配
 * @author Administrator
 *
 */
public class SessionTools {

    /**
     * 若cookie中没有sessionid,则生成sessionid分配，添加到cookie中，否则直接从cookie中取
     * @param request
     * @param response
     * @return
     */
    public static String getSessionId(HttpServletRequest request,HttpServletResponse response){
        
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length>0){
            for (Cookie cookie : cookies) {
                if("mysessionId".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        
        
        
        String mysessionId = UUID.randomUUID().toString().replaceAll("-", "");
        Cookie cookie = new Cookie("mysessionId",mysessionId);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        //把cookie写回浏览器
        response.addCookie(cookie);
        
        return mysessionId;
                
    }
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
