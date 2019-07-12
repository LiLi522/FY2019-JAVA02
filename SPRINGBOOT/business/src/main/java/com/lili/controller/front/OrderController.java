package com.lili.controller.front;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.lili.Service.IOrderService;
import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.pojo.User;
import com.lili.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping(value = "/order/")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /**
     * 创建订单
     */
    @RequestMapping(value = "/create/{shippingId}")
    public ServerResponse createOrder(@PathVariable("shippingId") Integer shippingId, HttpSession session) {
        User user = (User) session.getAttribute(Const.CONST_USER);

        if (user == null) {
            return ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN, "未登录");
        }
        return orderService.createOrder(user.getId(), shippingId);
    }

    /**
     * 支付接口
     * @param orderNo
     * @param session
     * @return
     */
    @RequestMapping(value = "pay/{orderNo}")
    public ServerResponse pay(@PathVariable("orderNo") Long orderNo, HttpSession session) {
        User user = (User) session.getAttribute(Const.CONST_USER);

        if (user == null) {
            return ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN, "未登录");
        }

        return orderService.pay(user.getId(), orderNo);

    }

    /**
     * 支付宝服务器回调商家服务器接口
     * @return
     */
    @RequestMapping(value = "callback.do")
    public String alipay_callback(HttpServletRequest request) {

        Map<String, String[]> callbackMap = request.getParameterMap();
        Map<String,String> signParams= Maps.newHashMap();

        Iterator<String> iterator = callbackMap.keySet().iterator();
        while (iterator.hasNext()) {
            String keys = iterator.next();
            String[] values = callbackMap.get(keys);
            StringBuffer sbuffer = new StringBuffer();
            if(values != null && values.length > 0){
                for(int i = 0; i<values.length ; i++){ // 1 ,2 ,3
                    sbuffer.append(values[i]);
                    if(i != values.length-1){
                        sbuffer.append(",");
                    }

                }
            }

            signParams.put(keys, sbuffer.toString());
        }

        //验签
        try {
            signParams.remove("sign_type");
            boolean result = AlipaySignature.rsaCheckV2(signParams, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());
            if(result){
                //验签通过
                System.out.println("通过");

                return orderService.callback(signParams);
            }else{
                return "fail";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
