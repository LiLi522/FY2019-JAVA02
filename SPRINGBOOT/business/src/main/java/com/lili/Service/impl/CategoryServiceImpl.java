package com.lili.Service.impl;

import com.google.common.collect.Sets;
import com.lili.Service.ICategoryService;
import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.dao.CategoryMapper;
import com.lili.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(Category category) {
        if (category == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "参数不能为空");
        }
        int result = categoryMapper.insert(category);
        if (result <= 0) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "添加品类失败");
        }
        return ServerResponse.serverResponseBysuccess();
    }

    @Override
    public ServerResponse updateCategory(Category category) {
        if (category == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "参数不能为空");
        }
        if (category.getId() == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "类别id必传");
        }
        int result = categoryMapper.updateByPrimaryKey(category);
        if (result <= 0) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "更新品类失败");
        }
        return ServerResponse.serverResponseBysuccess();
    }

    @Override
    public ServerResponse getCategoryById(Integer categoryId) {
        if (categoryId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "类别id必传");
        }
        List<Category> categoryList = categoryMapper.selectCategoryById(categoryId);

        return ServerResponse.serverResponseBySuccess(categoryList, "成功");
    }

    @Override
    public ServerResponse deepCategory(Integer categoryId) {
        if (categoryId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "类别id必传");
        }
        Set<Category> categorySet = Sets.newHashSet();
        //递归查询
        Set<Category> categorySet1 = findAllChildCategory(categorySet, categoryId);
        Set<Integer> categoryIds = Sets.newHashSet();
        Iterator<Category> categoryIterable = categorySet1.iterator();
        while (categoryIterable.hasNext()) {
            Category category = categoryIterable.next();
            categoryIds.add(category.getId());
        }
        return ServerResponse.serverResponseBySuccess(categoryIds);
    }

    @Override
    public ServerResponse<Category> selectCategory(Integer categoryId) {
        if (categoryId == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "类别id必传");
        }
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "商品类别不存在");
        }
        return ServerResponse.serverResponseBySuccess(category);
    }

    public Set<Category> findAllChildCategory(Set<Category> categorySet, Integer categoryId) {
        //查看categoryId的类别信息
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查看categoryId的平级子类
        List<Category> categoryList = categoryMapper.selectCategoryById(categoryId);
        if (categoryList != null && categoryList.size() > 0) {
            for (Category category1:categoryList) {
                findAllChildCategory(categorySet, category1.getId());
            }
        }
        return categorySet;
    }
}
