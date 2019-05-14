package com.dapeis.api.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dapeis.api.utils.ApiSignUtils;
import com.dapeis.core.utils.StylistConstants;
import com.google.gson.Gson;

/**
 * 统计处理超时的请求 Title:StylistApiInterceptor.java StylistApiInterceptor Description:
 * 
 * @author yuanyong
 * @date 2017年4月20日 下午4:22:43 Email: hzyuanyong@126.com
 *
 */
public class StylistApiInterceptor implements HandlerInterceptor {
	final Logger logger = LoggerFactory.getLogger(getClass());
	Gson gson = new Gson();
	ThreadLocal<Long> _tempStartMethodTime = new ThreadLocal<Long>();

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		String requestURI = request.getRequestURI();
		ApiSignUtils.checkSign(request,requestURI,ApiSignUtils.signKey);

		this._tempStartMethodTime.set(System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{

		long exeTime = System.currentTimeMillis() - this._tempStartMethodTime.get();

		if (exeTime >= 500) {// 500毫秒， 记录日志
			StylistConstants.Loggers.TIMEOUT.info("请求地址:{},参数：{},请求超时:{}",
					new Object[] { request.getRequestURI(), gson.toJsonTree(request.getParameterMap()).toString(), exeTime });
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
	}

}
