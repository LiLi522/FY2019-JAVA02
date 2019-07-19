package com.lili.interceptors;

import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.pojo.User;
import com.lili.utils.Const;
import com.lili.utils.JsonUtils;
import com.lili.utils.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拦截前台用户的请求
 */

@Component
public class PortalAuthorityInterceptor implements HandlerInterceptor {


    @Autowired
    RedisApi redisApi;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HttpSession session=request.getSession();
        //User user=(User) session.getAttribute(Const.CONST_USER);
        Cookie[] cookies=request.getCookies();
        if(cookies==null||cookies.length==0){
            return nologin(response);
        }

        for(Cookie c:cookies){
            if(c.getName().equals(Const.CONST_USER)){
                String token=c.getValue();//用户存在redis中的key
                String userjson=redisApi.get(token);
                if(userjson==null){
                    return nologin(response);
                }else{
                    User user=JsonUtils.string2Obj(userjson,User.class);
                    session.setAttribute(Const.CONST_USER,user);
                    return true;
                }

            }
        }

        return nologin(response);

    }


    private  boolean nologin(HttpServletResponse response){
        response.reset();
        try {
            response.addHeader("Content-Type","application/json;charset=UTF-8");
            PrintWriter printWriter=response.getWriter();
            ServerResponse serverResponse= ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN,"未登录");
            String json= JsonUtils.obj2String(serverResponse);
            printWriter.write(json);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("=portal==postHandle===");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("=portal==afterCompletion===");
    }
}
