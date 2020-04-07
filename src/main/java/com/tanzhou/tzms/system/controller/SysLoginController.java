package com.tanzhou.tzms.system.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tanzhou.tzms.common.service.SysShiroService;
import com.tanzhou.tzms.common.vo.CurrentUser;
import com.tanzhou.tzms.common.web.JsonResult;
import com.tanzhou.tzms.system.service.SysService;

@Controller
public class SysLoginController {
	@Autowired
	private SysShiroService loginService;
	
	@Autowired
	private SysService sysService;
	
	@RequestMapping("/loginUI")
	public String login(){
		return "login";
	}
	
	/**登录操作*/
//	@RequestMapping("/login")
//	@ResponseBody
//	public JsonResult login(HttpSession session ,String username,String password){
//		System.out.println(username+"/"+password);
////	    loginService.login(username, password);
//		CurrentUser user = sysService.authenticat(username, password);
//		session.setAttribute("currentUser", user);
//		return new JsonResult();
//	}
	
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(String username,String password){
		System.out.println(username+"/"+password);
		loginService.login(username, password);
		return new JsonResult();
	}
}
