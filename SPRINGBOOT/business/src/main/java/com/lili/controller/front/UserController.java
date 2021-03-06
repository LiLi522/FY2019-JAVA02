package com.lili.controller.front;


import com.lili.Service.IUserService;
import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.pojo.User;
import com.lili.utils.Const;
import com.lili.utils.JsonUtils;
import com.lili.utils.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.*;
import java.util.UUID;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    RedisApi redisApi;

    /**
     * 注册接口
     */
    @RequestMapping(value = "register.do")
    public ServerResponse userRegister(User user) {
        return userService.userRegister(user);
    }

    /**
     *用户登录接口
     */
    @RequestMapping(value = "login/{username}/{password}")
    public ServerResponse userLogin(@PathVariable("username") String username,
                                    @PathVariable("password") String password,
                                    HttpSession session, HttpServletResponse response) {
        ServerResponse serverResponse = userService.userLogin(username, password);
        //判断是否登录成功
        if (serverResponse.isSuccess()) {
            session.setAttribute(Const.CONST_USER, serverResponse.getData());

            String token = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(Const.CONST_USER, token);
            cookie.setDomain("www.xlxl.com");
            cookie.setPath("/");
            cookie.setMaxAge(7*24*356);
            response.addCookie(cookie);
            //将用户信息写到redis中
            redisApi.setex(token, 1800, JsonUtils.obj2String(serverResponse.getData()));
        }
        return serverResponse;
    }

    /**
     * 根据username获取密保问题接口
     */
    @RequestMapping(value = "forget_get_question/{username}")
    public  ServerResponse forgetGetQuestion(@PathVariable("username") String username) {
        return userService.forgetGetQuestion(username);
    }

    /**
     * 校验答案接口
     */
    @RequestMapping(value = "forget_check_answer.do")
    public ServerResponse forgetCheckAnswer(String username, String question, String answer) {
        return userService.forgetCheckAnswer(username, question, answer);
    }

    /**
     * 重置密码接口
     */
    @RequestMapping(value = "forget_reset_password.do")
    public ServerResponse forgetResetPassword(String username, String password_new, String forgettoken) {
        return userService.forgetResetPassword(username, password_new, forgettoken);
    }

    /**
     * 修改信息
     */
    @RequestMapping(value = "update_information.do")
    public ServerResponse updateInformation(User user, HttpSession session) {
        User loginUser = (User)session.getAttribute(Const.CONST_USER);

        user.setId(loginUser.getId());
        return userService.updateInformation(user);
    }
}
