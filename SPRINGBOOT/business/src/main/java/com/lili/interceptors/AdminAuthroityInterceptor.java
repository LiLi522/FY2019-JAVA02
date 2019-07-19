package com.lili.interceptors;

import com.lili.common.ResponseCode;
import com.lili.common.RoleEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.User;
import com.lili.utils.Const;
import com.lili.utils.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Component
public class AdminAuthroityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CONST_USER);
        if(user == null){
            response.reset();
            try {

                response.setHeader("Content-Type","application/json;charset=UTF-8");
                PrintWriter printWriter = response.getWriter();
                ServerResponse serverResponse = ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN,"未登录");
                String json = JsonUtils.obj2String(serverResponse);

                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        int role = user.getRole();
        if(role == RoleEnum.ROLE_USER.getRole()){

            response.reset();
            try {
                PrintWriter printWriter = response.getWriter();
                ServerResponse serverResponse = ServerResponse.serverResponseByError(ResponseCode.ERROR,"权限不足");
                String json = JsonUtils.obj2String(serverResponse);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("===postHandle===");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("===afterCompletion===");
    }
}
