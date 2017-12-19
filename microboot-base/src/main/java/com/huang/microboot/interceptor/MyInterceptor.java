package com.huang.microboot.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		System.err.println(">>>>>>>>>处理表单之前:"+handlerMethod.getBean().getClass().getName());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		System.err.println(">>>>>>>>>正在处理表单:"+handlerMethod.getBean().getClass().getName());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		System.err.println(">>>>>>>>>参数处理完毕:"+handlerMethod.getBean().getClass().getName());
	}

}
