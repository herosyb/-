package com.tanzhou.tzms.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tanzhou.tzms.common.vo.CurrentUser;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//http://localhost:8080/tzms/loginUI
		//如果用户直接要去登录页面，则以下操作都不需要做
		//获取当前的请求路径
		String url = request.getRequestURI();
		if(url.indexOf("login")>0){
			return true;//
		}
		HttpSession session = request.getSession();
		CurrentUser user = (CurrentUser)session.getAttribute("currentUser");
		//判断session中是否有登录对象
		if(user==null){
			//没有登录
			//去登录页面
			request.getRequestDispatcher("/loginUI").forward(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	
	
}
