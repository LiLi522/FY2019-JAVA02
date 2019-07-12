package com.lili.controller.front;

import com.lili.Service.IShippingService;
import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.pojo.Shipping;
import com.lili.pojo.User;
import com.lili.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/shipping/")
public class ShippingController {

    @Autowired
    IShippingService shippingService;
    /**
     * 添加地址
     */
    @RequestMapping(value = "/add.do")
    public ServerResponse addAddress(Shipping shipping, HttpSession session) {
        User user = (User) session.getAttribute(Const.CONST_USER);

        if (user == null) {
            return ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN, "未登录");
        }
        shipping.setUserId(user.getId());
        return shippingService.addAddress(shipping);
    }
}
