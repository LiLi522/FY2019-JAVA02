package com.lili.controller.backend;

import com.lili.Service.IProductService;
import com.lili.common.ResponseCode;
import com.lili.common.RoleEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Product;
import com.lili.pojo.User;
import com.lili.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/product/")
public class ProductController {


    @Autowired
    IProductService productService;

    /**
     * 商品添加&更新
     */
    @RequestMapping(value = "save.do")
    public ServerResponse addOrUpdate(Product product, HttpSession session) {

        return productService.addOrUpdate(product);
    }

    /**
     * 添加商品
     * @param productName
     * @param productId
     * @param pageNum(default=1)
     * @param pageSize(default=10)
     */
    @RequestMapping(value = "search.do")
    public ServerResponse search(@RequestParam(name = "productName", required = false) String productName,
                                 @RequestParam(name = "productId", required = false) Integer productId,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 HttpSession session) {

        return productService.search(productName, productId, pageNum, pageSize);
    }

    /**
     * 商品详情
     * @param productId
     * @return
     */
    @RequestMapping(value = "/{productId}")
    public ServerResponse detail(@PathVariable("productId") Integer productId, HttpSession session) {

        return productService.detail(productId);
    }
}
