package com.lili.Service;


import com.lili.common.ServerResponse;
import com.lili.pojo.User;


public interface IUserService {
    /**
     * 注册接口
     * @param user
     * @return ServerResponse
     */
    public ServerResponse userRegister(User user);

    /**
     * 登录接口
     * @param username password
     * @return ServerResponse
     */
    public ServerResponse userLogin(String username, String password);

    /**
     * 管理员登录接口
     */
    public ServerResponse adminLogin(String username, String password, int type);

    /**
     *根据username获取密保问题
     */
    public  ServerResponse forgetGetQuestion(String username);

    /**
     * 提交答案接口
     */
    public ServerResponse forgetCheckAnswer(String username, String question, String answer);

    /**
     * 重置密码接口
     */
    public ServerResponse forgetResetPassword(String username, String password_new, String forgettoken);

    /**
     * 更新用户信息
     */
    public ServerResponse updateInformation(User user);
}
