package com.lili.Service;

import com.lili.common.ServerResponse;
import com.lili.pojo.Shipping;

public interface IShippingService {
    /**
     * 添加收货地址
     * @param shipping
     * @return
     */
    public ServerResponse addAddress(Shipping shipping);

    /**
     * 根据Id查询
     * @param shippingId
     * @return
     */
    public ServerResponse findShippingById(Integer shippingId);
}
