package com.yizhidu.service.impl;


import com.yizhidu.mapper.UserMapper;
import com.yizhidu.pojo.Category;
import com.yizhidu.pojo.Topics;
import com.yizhidu.pojo.User;
import com.yizhidu.service.UserService;
import com.yizhidu.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public int userRegist(User user){//用户注册
       return userMapper.addUser(user);
    }

    @Override
    public Msg selUser(String username) {   //根据用户名查询用户信息
        User user ;
        Msg msg;
        if (username != null){
            user = userMapper.selUser(username);

            if (user != null ){
               msg  = Msg.success();
               msg.setMsg("该用户名可用!");
            }else {
               msg = Msg.fail();
               msg.setMsg("该用户名被占用!");

            }
            return msg;
     }
        return null;
    }

    @Override
    public List<User> userLogin(User user) {      //用户登录
        return userMapper.selUserByUP(user);

    }

    @Override
    public List<Category> selCates() {      //试题分类信息查询
        return  userMapper.selCates();
    }

    @Override
    public List<Topics> selTopicByCid(int cid) {
        return userMapper.selTopicByCid(cid);
    }

}
