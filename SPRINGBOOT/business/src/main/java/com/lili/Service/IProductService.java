package com.lili.Service;

import com.lili.common.ServerResponse;
import com.lili.pojo.Cart;
import com.lili.pojo.Product;
import com.lili.vo.ProductDetailVO;

import java.util.List;


public interface IProductService {

    /**
     * 添加&更新商品
     */
    public ServerResponse addOrUpdate(Product product);

    /**
     * 搜索商品
     *
     */
    public ServerResponse search(String productName,
                                 Integer productId,
                                 Integer pageNum,
                                 Integer pageSize);

    /**
     * 商品详情
     */
    public ServerResponse<ProductDetailVO> detail(Integer productId);

    /**
     * 商品详情
     */
    public ServerResponse<Product> findProductByProductId(Integer productId);

    /**
     * 根据商品id来查询商品库存信息
     */
    public ServerResponse<Product> findProductById(Integer productId);



    /**
     * 批量删除购物车商品
     * @param
     * @return
     */
    public ServerResponse reduceSotck(Integer productId,Integer stock);

}
