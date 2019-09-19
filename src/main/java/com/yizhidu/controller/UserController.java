package com.yizhidu.controller;

import com.yizhidu.pojo.Category;
import com.yizhidu.pojo.Topics;
import com.yizhidu.pojo.User;
import com.yizhidu.service.UserService;
import com.yizhidu.utils.CookUtils;
import com.yizhidu.utils.Msg;
import com.yizhidu.utils.UUIDUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Request;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/userRegist", method = RequestMethod.POST)
		public String userRegist(User user) {				//用户注册
		user.setUid(UUIDUtils.getId().toString());
//System.out.println(user.toString());
		userService.userRegist(user);
		//跳转到登录页面
		return "login";
	}


	@RequestMapping(value = "/selUser" ,method = RequestMethod.POST)		//根据用户名查询用户信息
	@ResponseBody
	public Msg selUser(String username){				//用户名检测
		return userService.selUser(username);
	}

	@RequestMapping(value = "/userLogin" ,method = RequestMethod.POST)		//用户登录
	public String userLogin(User user,String autoLogin, HttpServletRequest request,HttpServletResponse response){

	System.out.println("username:"+user.getUsername()+"------"+"password:"+user.getPassword());

	//获取登录用户信息
	List<User> list = userService.userLogin(user);


	if(list.size() == 1){
		//存在该用户,获取该用户
		User loginUser = list.get(0);

		//判断是否选中自动登录 (有两种状态:on和null)
		if (autoLogin != null){
			//将账号密码存入cookie
			Cookie cookie = new Cookie("autoLogin",loginUser.getUsername()+"#"+loginUser.getPassword());
	System.out.println("cookie中的拼接:"+loginUser.getUsername()+"#"+loginUser.getPassword());
			cookie.setMaxAge(60*60*24*7);//设置7天有效
			response.addCookie(cookie);//cookie存客户端
		}

		//登录成功:将用户信息存入session中
		request.getSession().setAttribute("loginUser",loginUser);
		//登录成功,跳转到题型列表页面
		return "person_information";

	}else {
		//登录失败:将失败信息存入session中
		String msg = "用户名或密码错误!";
		request.setAttribute("msg",msg);

		//将错误信息返回到登录界面
		return "login";
		}
	}

	@RequestMapping(value = "/userLogout")		//退出登录
	public void userLogout(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//让session中的用户信息失效
		request.getSession().invalidate();
		//让cookie中的用户密码失效
		Cookie[] cookies = request.getCookies();
		Cookie autoLogin = CookUtils.getCookieByName("autoLogin", cookies);

		if (autoLogin != null){
			autoLogin.setMaxAge(0);
			response.addCookie(autoLogin);
		}
		//重定向到首页
		request.getRequestDispatcher("index.jsp").forward(request,response);
	}

	@RequestMapping(value = "/selCates",method = RequestMethod.POST)	//查询试题分类信息
	public void selCates(HttpServletResponse response) throws IOException {
		List<Category> list = userService.selCates();
		JSONArray jsonArray = JSONArray.fromObject(list);
		String jsonStr = jsonArray.toString();

		System.out.println("jsonStr:"+jsonStr);

		response.setContentType("application/json;charset=utf-8");

		response.getWriter().write(jsonStr);//response向本页面提交json字符串
//        return jsonStr;
	}

    @RequestMapping(value = "/selTopicByCid",method = RequestMethod.GET)    //按cid查询题目
	public String selTopicByCid(int cid,HttpServletRequest request){
//        System.out.println("cid="+cid);
        List<Topics> list = userService.selTopicByCid(cid);

        for (Topics topic :
                list) {
            System.out.println(topic.toString());
        }

        request.setAttribute("list",list);
        return "topic_info";
    }


}
