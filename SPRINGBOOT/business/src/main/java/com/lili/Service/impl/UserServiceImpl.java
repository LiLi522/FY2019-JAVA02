package com.lili.Service.impl;

import com.lili.Service.IUserService;
import com.lili.common.ResponseCode;
import com.lili.common.RoleEnum;
import com.lili.common.ServerResponse;
import com.lili.dao.UserMapper;
import com.lili.pojo.User;
import com.lili.utils.MD5Utils;
import com.lili.utils.RedisApi;
import com.lili.utils.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisApi redisApi;

    @Override
    public ServerResponse userRegister(User user) {
        //step1:参数校验
        if (user == null) {
            return ServerResponse.serverResponseByError(ResponseCode.PARAM_NOT_NULL, "参数不能为空");
        }
        //step2:判断用户名是否存在
        int resultUsername  = userMapper.isExistUsername(user.getUsername());
        if (resultUsername > 0) {//用户名已存在
            return ServerResponse.serverResponseByError(ResponseCode.USERNAME_EXISTS, "用户名已存在");
        }
        //step3:判断邮箱是否存在
        int resultEmail  = userMapper.isExistEmail(user.getEmail());
        if (resultEmail > 0) {//邮箱已存在
            return ServerResponse.serverResponseByError(ResponseCode.EMAIL_EXISTS, "邮箱已存在");
        }
        //step4:密码加密，设置用户角色
        user.setPassword(MD5Utils.getMD5Code(user.getPassword()));
        //设置角色权限
        user.setRole(RoleEnum.ROLE_USER.getRole());
        //step5:注册
        int insertResult = userMapper.insert(user);
        if (insertResult <= 0) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "注册失败");
        }
        //step6:返回
        return ServerResponse.serverResponseBysuccess();
    }

    @Override
    public ServerResponse userLogin(String username, String password) {
        //step1:参数校验
        if (username == null || username.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名不能为空");
        }
        if (password == null || password.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "密码不能为空");
        }
        //step2:判断用户名是否存在
        int resultUsername = userMapper.isExistUsername(username);
        if (resultUsername <= 0) {//用户名不存在
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名不存在");
        }
        //step3:密码加密
        password = MD5Utils.getMD5Code(password);
        //step4:登录
        User user = userMapper.isExistsByUsernameAndPassword(username, password);
        if (user == null) {//密码错误
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "密码错误");
        }
        return ServerResponse.serverResponseBySuccess(user);
    }

    @Override
    public ServerResponse adminLogin(String username, String password, int type) {
        //step1:参数校验
        if (username == null || username.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名不能为空");
        }
        if (password == null || password.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "密码不能为空");
        }
        //step2:判断用户名是否存在
        int resultUsername = userMapper.isExistUsername(username);
        if (resultUsername <= 0) {//用户名不存在
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名不存在");
        }
        //step3:密码加密
        password = MD5Utils.getMD5Code(password);
        //step4:登录
        User user = userMapper.isExistsByUsernameAndPassword(username, password);
        if (user == null) {//密码错误
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "密码错误");
        }
        if (type == 0) {//管理员
            if (user.getRole() != RoleEnum.ROLE_ADMIN.getRole()) { //没有管理员权限
                return ServerResponse.serverResponseByError(ResponseCode.ERROR, "没有权限");
            }
        }
        return ServerResponse.serverResponseBySuccess(user);
    }

    @Override
    public ServerResponse forgetGetQuestion(String username) {
        //step1：参数判断
        if (username == null || username.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名不能为空");
        }
        //step2：根据用户名查询问题
        String question = userMapper.forgetGetQuestion(username);
        if (question == null) {//没有查询到问题
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "没有查询到保密问题");
        }
        //step3：返回结果
        return ServerResponse.serverResponseBySuccess(question);
    }

    @Override
    public ServerResponse forgetCheckAnswer(String username, String question, String answer) {
        //step1：参数判断
        if (username == null || username.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名不能为空");
        }
        if (question == null || question.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "密保问题不能为空");
        }
        if (answer == null || answer.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "答案不能为空");
        }
        //step2：查询
        int result = userMapper.forgetCheckAnswer(username, question, answer);
        if (result <= 0) {//没有查询到问题
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "回答错误");
        }
        //step3：返回结果
        //生成token
        String token = UUID.randomUUID().toString();
        //guava
        //TokenCache.set("username:" + username, token);
        //redis
        redisApi.setex("username:" + username, 12*3600, token);
        return ServerResponse.serverResponseBySuccess(token);
    }

    @Override
    public ServerResponse forgetResetPassword(String username, String password_new, String forgettoken) {
        //step1：参数判断
        if (username == null || username.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名不能为空");
        }
        if (password_new == null || password_new.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "新密码不能为空");
        }
        if (forgettoken == null || forgettoken.equals("")) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "token不能为空");
        }
        //判断是否是修改自己的密码
        //Guava : String token = TokenCache.get("username:" + username);
        //redis
        String token = redisApi.get("username:" + username);
        if (token == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "不能修改别人的密码或者token已经失效");
        }
        if (!token.equals(forgettoken)) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "token无效");
        }
        //step2：更新
        int result = userMapper.forgetResetPassword(username, MD5Utils.getMD5Code(password_new));
        if (result <= 0) {//重置失败
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "密码修改失败");
        }
        //step3：返回结果
        return ServerResponse.serverResponseBysuccess();
    }

    @Override
    public ServerResponse updateInformation(User user) {
        //判断参数是否为空
        if (user == null) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "参数不能为空");
        }
        int result = userMapper.updateUserByActivate(user);
        if (result <= 0) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "修改失败");
        }
        return ServerResponse.serverResponseBysuccess();
    }
}
