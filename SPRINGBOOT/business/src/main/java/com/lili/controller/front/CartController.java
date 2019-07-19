package com.lili.controller.front;

import com.lili.Service.ICartService;
import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.pojo.User;
import com.lili.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/cart/")
public class CartController {

    @Autowired
    ICartService cartService;
    /**
     * 商品添加到购物车
     */
    @RequestMapping(value = "add/{productId}/{count}")
    public ServerResponse addCart(@PathVariable("productId") Integer productId,
                                  @PathVariable("count") Integer count,
                                  HttpSession session) {
        User user = (User) session.getAttribute(Const.CONST_USER);



        return cartService.addCart(user.getId(), productId, count);
    }

}
