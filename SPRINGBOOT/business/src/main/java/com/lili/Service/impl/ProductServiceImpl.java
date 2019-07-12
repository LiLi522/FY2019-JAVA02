package com.lili.Service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.lili.Service.ICategoryService;
import com.lili.Service.IProductService;
import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.dao.ProductMapper;
import com.lili.pojo.Cart;
import com.lili.pojo.Category;
import com.lili.pojo.Product;
import com.lili.utils.DateUtils;
import com.lili.vo.ProductDetailVO;
import com.lili.vo.ProductListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Value("${business.imageHost}")
    private String imageHost;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    ProductMapper productMapper;


    @Override
    public ServerResponse addOrUpdate(Product product) {
        if (product == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "参数不能为空");
        }
        //subimages 1.png,2.png,3.png
        //step2：设置商品主图sub_images -->1.jpg,2.jpg,3.jpg

        String subImages = product.getSubImages();
        if (subImages != null && "".equals(subImages)) {
            String[] subImageArr = subImages.split(",");
            if (subImageArr.length > 0) {
                //设置商品的主图
                product.setMainImage(subImageArr[0]);
            }
        }

        Integer productId = product.getId();
        if (productId == null) {//添加
            int result = productMapper.insert(product);
            if (result <= 0) {
                return ServerResponse.serverResponseByError(ResponseCode.ERROR, "添加失败");
            }else {
                return ServerResponse.serverResponseBysuccess();
            }
        }else {//更新
            int result = productMapper.updateByPrimaryKey(product);
            if (result <= 0) {
                return ServerResponse.serverResponseByError(ResponseCode.ERROR, "更新失败");
            }else {
                return ServerResponse.serverResponseBysuccess();
            }
        }
    }

    @Override
    public ServerResponse search(String productName, Integer productId, Integer pageNum, Integer pageSize) {

        if (productName != null) {
            productName = "%" + productName + "%";
        }
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.findProductByNameAndId(productId, productName);
        List<ProductListVO> productListVOLsit = Lists.newArrayList();
        // List<Product>-> List<ProductListVO>
        if(productList!=null&&productList.size()>0){
            for(Product product:productList){
                //proudct->productListVO
                ProductListVO productListVO = assembleProductListVO(product);
                productListVOLsit.add(productListVO);
            }
        }
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(productListVOLsit);
        return ServerResponse.serverResponseBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<ProductDetailVO> detail(Integer productId) {
        if (productId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "id必传");
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.serverResponseBySuccess("不存在该商品");
        }
        //product->productDetailVO
        ProductDetailVO productDetailVO = assembleProductDetailVO(product);

        return ServerResponse.serverResponseBySuccess(productDetailVO);
    }

    @Override
    public ServerResponse<Product> findProductByProductId(Integer productId) {
        if (productId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "id必传");
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.serverResponseBySuccess("不存在该商品");
        }


        return ServerResponse.serverResponseBySuccess(product);
    }

    @Override
    public ServerResponse<Product> findProductById(Integer productId) {
        if (productId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "商品id必传");
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "商品不存在");
        }
        return ServerResponse.serverResponseBySuccess(product);
    }

    @Override
    public ServerResponse reduceSotck(Integer productId, Integer stock) {
        if (productId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "商品id必传");
        }
        if (stock == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "库存参数必传");
        }
        int result = productMapper.reduceProductStock(productId, stock);
        if (result <= 0) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "扣库存失败");
        }
        return ServerResponse.serverResponseBysuccess();
    }

    private ProductDetailVO assembleProductDetailVO(Product product){


        ProductDetailVO productDetailVO=new ProductDetailVO();
        productDetailVO.setCategoryId(product.getCategoryId());
        productDetailVO.setCreateTime(DateUtils.dateToStr(product.getCreateTime()));
        productDetailVO.setDetail(product.getDetail());
        productDetailVO.setImageHost(imageHost);
        productDetailVO.setName(product.getName());
        productDetailVO.setMainImage(product.getMainImage());
        productDetailVO.setId(product.getId());
        productDetailVO.setPrice(product.getPrice());
        productDetailVO.setStatus(product.getStatus());
        productDetailVO.setStock(product.getStock());
        productDetailVO.setSubImages(product.getSubImages());
        productDetailVO.setSubtitle(product.getSubtitle());
        productDetailVO.setUpdateTime(DateUtils.dateToStr(product.getUpdateTime()));
        // Category category= categoryMapper.selectByPrimaryKey(product.getCategoryId());
        ServerResponse<Category> serverResponse = categoryService.selectCategory(product.getCategoryId());
        Category category=serverResponse.getData();
        if(category!=null){
            productDetailVO.setParentCategoryId(category.getParentId());
        }
        return productDetailVO;
    }

    private ProductListVO assembleProductListVO(Product product){
        ProductListVO productListVO = new ProductListVO();
        productListVO.setId(product.getId());
        productListVO.setCategoryId(product.getCategoryId());
        productListVO.setMainImage(product.getMainImage());
        productListVO.setName(product.getName());
        productListVO.setPrice(product.getPrice());
        productListVO.setStatus(product.getStatus());
        productListVO.setSubtitle(product.getSubtitle());
        return  productListVO;
    }
}
