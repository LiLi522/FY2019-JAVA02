package com.lili.Service.impl;

import com.google.common.collect.Lists;
import com.lili.Service.ICartService;
import com.lili.Service.IProductService;
import com.lili.common.CheckEnum;
import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.dao.CartMapper;
import com.lili.pojo.Cart;
import com.lili.pojo.Product;
import com.lili.utils.BigDecimalUtils;
import com.lili.vo.CartProductVO;
import com.lili.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    IProductService productService;

    @Autowired
    CartMapper cartMapper;

    @Override
    public ServerResponse addCart(Integer userId, Integer productId, Integer count) {
        if (productId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "商品id必传");
        }
        if (count == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "商品数量不能为0");
        }

        ServerResponse<Product> serverResponse = productService.findProductById(productId);

        if (!serverResponse.isSuccess()) {//商品不存在
            return ServerResponse.serverResponseByError(serverResponse.getStatus(), serverResponse.getMsg());
        } else {
            Product product = serverResponse.getData();
            if (product.getStock() <= 0) {
                return ServerResponse.serverResponseByError(ResponseCode.ERROR, "商品已售罄");
            }
        }
        Cart cart = cartMapper.findCartByProductIdAndUserId(userId, productId);
        if (cart == null) {
            //添加
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setProductId(productId);
            newCart.setQuantity(count);
            newCart.setChecked(CheckEnum.CART_PRODUCT_CHECK.getCheck());
            int result = cartMapper.insert(newCart);
            if (result <= 0) {
                return ServerResponse.serverResponseByError(ResponseCode.ERROR, "添加失败");
            }
        } else {
            //更新商品在购物车中的数量
            cart.setQuantity(cart.getQuantity() + count);
            int result = cartMapper.updateByPrimaryKey(cart);
            if (result <= 0) {
                return ServerResponse.serverResponseByError(ResponseCode.ERROR, "更新失败");
            }
        }

        //step4: 封装购物车对象CartVO
        CartVO cartVO = getCartVO(userId);

        //step5:返回CartVO
        return ServerResponse.serverResponseBySuccess(cartVO);
    }

    @Override
    public ServerResponse<List<Cart>> findByUserId(Integer userId) {
        if (userId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户id必传");
        }
        List<Cart> cartList = cartMapper.findCartsByUserIdAndChecked(userId);

        return ServerResponse.serverResponseBySuccess(cartList);
    }

    @Override
    public ServerResponse deleteBatch(List<Cart> cartList) {
        if(cartList==null||cartList.size()==0){
            return ServerResponse.serverResponseByError(ResponseCode.ERROR,"要删除的购物车商品不能为空");
        }

        int result= cartMapper.deleteBatch(cartList);
        if(result!=cartList.size()){
            return ServerResponse.serverResponseByError(ResponseCode.ERROR,"购物车清空失败");
        }
        return ServerResponse.serverResponseBysuccess();
    }

    private CartVO getCartVO(Integer userId) {
        CartVO cartVO = new CartVO();
        //step1:根据userId查询该用户的购物信息
        List<Cart> cartList = cartMapper.findByUserId(userId);
        if (cartList == null || cartList.size() <= 0) {
            return cartVO;
        }

        //step2:List<Cart> --> List<CartProductVO>
        for (Cart cart:cartList) {
            CartProductVO cartProductVO = new CartProductVO();

        }
        //定义购物车商品总价格
        BigDecimal cartTotalPrice=new BigDecimal("0");
        //step2: List<Cart> --> List<CartProductVO>

        List<CartProductVO> cartProductVOList= Lists.newArrayList();
        int limit_quantity=0;
        String limitQuantity=null;
        for(Cart cart:cartList){
            //Cart-->CartProductVO
            CartProductVO cartProductVO = new CartProductVO();
            cartProductVO.setId(cart.getId());
            cartProductVO.setUserId(userId);
            cartProductVO.setProductId(cart.getProductId());

            ServerResponse<Product> serverResponse = productService.findProductById(cart.getProductId());
            if(serverResponse.isSuccess()){
                Product product = serverResponse.getData();
                if(product.getStock() >= cart.getQuantity()){
                    limit_quantity = cart.getQuantity();
                    limitQuantity = "LIMIT_NUM_SUCCESS";
                }else{
                    limit_quantity = product.getStock();
                    limitQuantity = "LIMIT_NUM_FAIL";
                }
                cartProductVO.setQuantity(limit_quantity);
                cartProductVO.setLimitQuantity(limitQuantity);
                cartProductVO.setProductName(product.getName());
                cartProductVO.setProductSubtitle(product.getSubtitle());
                cartProductVO.setProductMainImage(product.getMainImage());
                cartProductVO.setProductPrice(product.getPrice());
                cartProductVO.setProductStatus(product.getStatus());
                cartProductVO.setProductTotalPrice(BigDecimalUtils.mul(product.getPrice().doubleValue(),
                        cart.getQuantity()*1.0));
                cartProductVO.setProductStock(product.getStock());
                cartProductVO.setProductChecked(cart.getChecked());
                cartProductVOList.add(cartProductVO);

                if(cart.getChecked() == CheckEnum.CART_PRODUCT_CHECK.getCheck()){
                    //商品被选中
                    cartTotalPrice= BigDecimalUtils.add(cartTotalPrice.doubleValue(),cartProductVO.getProductTotalPrice().doubleValue());
                }

            }

        }

        cartVO.setCartProductVOList(cartProductVOList);

        //step3:计算购物车总得价格
        cartVO.setCarttotalprice(cartTotalPrice);

        //step4:判断是否全选
        Integer isAllChecked = cartMapper.isAllChecked(userId);

        if(isAllChecked == 0){
            //全选
            cartVO.setIsallchecked(true);
        }else {
            cartVO.setIsallchecked(false);
        }
        //step5:构建CartVO
        return cartVO;
    }
}
