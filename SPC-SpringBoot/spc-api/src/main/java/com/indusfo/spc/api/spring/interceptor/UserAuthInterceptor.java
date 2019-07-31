package com.indusfo.spc.api.spring.interceptor;

import com.indusfo.spc.api.service.UserService;
import com.indusfo.spc.api.utils.UserThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Descirption 用户登录拦截入口
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/29 01:16
 */
public class UserAuthInterceptor implements HandlerInterceptor {

    public static UserThreadLocal userThreadLocal = new UserThreadLocal();

    @Resource
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        Integer userID =

    }




}
