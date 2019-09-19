package com.yizhidu.filter;


import com.yizhidu.pojo.User;
import com.yizhidu.service.UserService;
import com.yizhidu.service.impl.UserServiceImpl;
import com.yizhidu.utils.CookUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "autoLoginFilter")
public class autoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //只有httpservletrequest能获取cookie对象
        HttpServletRequest req = (HttpServletRequest) request;

       //获取session中存入的User对象loginUser
        User loginUser = (User) req.getSession().getAttribute("loginUser");

        //判断当前是否正在登录状态
        if (loginUser != null){
            //正在登录状态,放行
            chain.doFilter(req,resp);

        }else {
            //当前非正在登录状态

            //获取自动登录cookie
            Cookie[] cookies = req.getCookies();
            Cookie autoLogin = CookUtils.getCookieByName("autoLogin", cookies);

            //判断是否第一次登录
            if (autoLogin == null){

                //第一次登录,放行
                chain.doFilter(req,resp);

            }else {
                //不是第一次登录

                //拆分cookie中的账号密码
                String loginValue = autoLogin.getValue();
                String username = loginValue.split("#")[0];
                String password = loginValue.split("#")[1];

                System.out.println(username+"---"+password);

                //将账号密码封装进User
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);

                try {
                    //通过封装的user进行登录
                    ServletContext sc = req.getSession().getServletContext();//获取servletContext对象
                    WebApplicationContext cxt =WebApplicationContextUtils.getWebApplicationContext(sc);
                    UserService userService =  cxt.getBean(UserService.class);

                    List<User> list = userService.userLogin(user);

                    if (list.size() == 1){
                        User loginUser2 = list.get(0);
                        System.out.println("正在登录的用户详细详细:"+loginUser2.toString());

                        //将登录返回的User数据存入session
                        req.getSession().setAttribute("loginUser",loginUser2);
                    }

                    //放行
                    chain.doFilter(req,resp);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
