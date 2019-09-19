package com.yizhidu.service;

import com.yizhidu.pojo.Category;
import com.yizhidu.pojo.Topics;
import com.yizhidu.pojo.User;
import com.yizhidu.utils.Msg;

import java.util.List;

public interface UserService {


    public int userRegist(User user);//用户注册
    public Msg selUser(String username);//检验用户名
    public List<User> userLogin(User user);//用户登录
    public List<Category> selCates();//所有分类查询
    public List<Topics> selTopicByCid(int cid);//根据cid查询试题信息

}

