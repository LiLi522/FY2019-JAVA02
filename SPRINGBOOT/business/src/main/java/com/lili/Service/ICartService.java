package com.lili.Service;

import com.lili.common.ServerResponse;
import com.lili.pojo.Cart;

import java.util.List;


public interface ICartService {
    /**
     * 添加商品到购物车
     */
    public ServerResponse addCart(Integer userId,Integer productId, Integer count);

    /**
     * 根据用户id查看用户已选中的商品
     * @param userId
     * @return
     */
    public ServerResponse<List<Cart>> findByUserId(Integer userId);

    /**
     * 批量删除购物车商品
     * */
    public ServerResponse deleteBatch(List<Cart> cartList);
}
