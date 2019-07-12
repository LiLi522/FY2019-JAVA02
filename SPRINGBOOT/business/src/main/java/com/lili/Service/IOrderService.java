package com.lili.Service;

import com.lili.common.ServerResponse;

import java.util.Map;

public interface IOrderService {
    /**
     * 创建订单
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerResponse createOrder(Integer userId, Integer shippingId);

    /**
     * 支付
     * @param userId
     * @param orderNo
     * @return
     */
    public ServerResponse pay(Integer userId, Long orderNo);


    /**
     * 支付宝回调接口
     * @param requestParams
     * @return
     */
    public String callback(Map<String, String> requestParams);
}
