package com.lili.Service;

import com.lili.common.ServerResponse;
import com.lili.pojo.Category;


public interface ICategoryService {
    /**
     * 增加商品类别
     */
    public ServerResponse addCategory(Category category);

    /**
     * 修改品类名字
     * categoryId
     * categoryName
     * categoryURL
     */
    public ServerResponse updateCategory(Category category);

    /**
     * 查看平级类别
     * categoryId
     */
    public ServerResponse getCategoryById(Integer categoryId);

    /**
     * 查看平级类别
     * categoryId
     */
    public ServerResponse deepCategory(Integer categoryId);

    /**
     * 根据id查询类别
     */
    public  ServerResponse<Category> selectCategory(Integer categoryId);
}
