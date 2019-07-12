package com.lili.Service.impl;

import com.lili.Service.IShippingService;
import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.dao.ShippingMapper;
import com.lili.pojo.Shipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    ShippingMapper shippingMapper;

    @Override
    public ServerResponse addAddress(Shipping shipping) {
        if (shipping == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "参数不能为空");
        }
        Integer shippingId = shipping.getId();
        if (shippingId == null) {
            //添加
            int result = shippingMapper.insert(shipping);
            if (result <= 0) {
                return ServerResponse.serverResponseByError(ResponseCode.ERROR, "添加地址失败");
            } else {
                return ServerResponse.serverResponseBySuccess(shipping.getId());
            }
        } else {
            //修改
            int result = shippingMapper.updateByPrimaryKey(shipping);
            if (result <= 0) {
                return ServerResponse.serverResponseByError(ResponseCode.ERROR, "更新失败");
            }
        }
        return null;
    }

    @Override
    public ServerResponse findShippingById(Integer shippingId) {
        if (shippingId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "参数不能为空");
        }
        Shipping shipping = shippingMapper.selectByPrimaryKey(shippingId);
        if (shipping == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "收货地址不存在");
        }

        return ServerResponse.serverResponseBySuccess(shipping);
    }
}
