package com.yizhidu.mapper;
import com.yizhidu.pojo.Category;
import com.yizhidu.pojo.Topics;
import com.yizhidu.pojo.User;

import java.util.List;

public interface UserMapper {

	public int addUser(User user);//用户注册
	public User selUser(String username);//用户名检验
	public List<User> selUserByUP(User user);//用户登录
	public List<Category> selCates();//试题分类查询
	public List<Topics> selTopicByCid(int cid);//根据cid查询试题信息

}
