package com.lili.controller.backend;

import com.lili.Service.IUserService;
import com.lili.common.RoleEnum;
import com.lili.common.ServerResponse;
import com.lili.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/user/")
public class AdminController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "login/{username}/{password}")
    public ServerResponse userLogin(@PathVariable("username") String username,
                                    @PathVariable("password") String password,
                                    HttpSession session) {
        ServerResponse serverResponse = userService.adminLogin(username, password, RoleEnum.ROLE_ADMIN.getRole());
        //判断是否登录成功
        if (serverResponse.isSuccess()) {
            session.setAttribute(Const.CONST_USER, serverResponse.getData());
        }
        return serverResponse;
    }
}
