package com.lili.controller.backend;

import com.lili.Service.ICategoryService;
import com.lili.common.ResponseCode;
import com.lili.common.RoleEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Category;
import com.lili.pojo.User;
import com.lili.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping(value = "/manage/category/")
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;


    /**
     * 增加商品类别
     */
    @RequestMapping(value = "add_category.do")
    public ServerResponse addCategory(Category category, HttpSession session) {
        User user = (User) session.getAttribute(Const.CONST_USER);

        if (user == null) {
            return ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN, "未登录");
        }
        if (user.getRole() != RoleEnum.ROLE_ADMIN.getRole()) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "权限不足");
        }
        return iCategoryService.addCategory(category);
    }

    /**
     * 修改品类名字
     * categoryId
     * categoryName
     * categoryURL
     */
    @RequestMapping(value = "set_category.do")
    public ServerResponse updateCategory(Category category, HttpSession session) {
        User user = (User) session.getAttribute(Const.CONST_USER);

        if (user == null) {
            return ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN, "未登录");
        }
        if (user.getRole() != RoleEnum.ROLE_ADMIN.getRole()) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "权限不足");
        }
        return iCategoryService.updateCategory(category);
    }

    /**
     * 查看平级类别
     * categoryId
     */
    @RequestMapping(value = "/{categoryId}")
    public ServerResponse getCategoryById(@PathVariable("categoryId") Integer categoryId, HttpSession session) {
        User user = (User) session.getAttribute(Const.CONST_USER);

        if (user == null) {
            return ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN, "未登录");
        }
        if (user.getRole() != RoleEnum.ROLE_ADMIN.getRole()) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "权限不足");
        }
        return iCategoryService.getCategoryById(categoryId);
    }

    /**
     * 递归查看平级类别
     * categoryId
     */
    @RequestMapping(value = "/deep/{categoryId}")
    public ServerResponse deepCategory(@PathVariable("categoryId") Integer categoryId, HttpSession session) {
        User user = (User) session.getAttribute(Const.CONST_USER);

        if (user == null) {
            return ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN, "未登录");
        }
        if (user.getRole() != RoleEnum.ROLE_ADMIN.getRole()) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "权限不足");
        }
        return iCategoryService.deepCategory(categoryId);
    }
}
