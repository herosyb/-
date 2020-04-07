package com.tanzhou.tzms.common.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tanzhou.tzms.common.vo.CurrentUser;
import com.tanzhou.tzms.common.web.JsonResult;
import com.tanzhou.tzms.system.domain.SysUser;
import com.tanzhou.tzms.system.service.SysUserService;

@Controller
public class IndexController {
	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/indexUI")
	public String indexUI(Model model){
		System.out.println("indexUI");
		//返回的字符串会对应一个/WEB-INF/pages/index.jsp页面
		
		//从shiro中取出用户身份信息
		Subject subject = SecurityUtils.getSubject();
		CurrentUser user = (CurrentUser)subject.getPrincipal();
		model.addAttribute("currentUser", user);
		return "index";
	}
	
	@RequestMapping("/doFindUserMenus")
	@ResponseBody
	public JsonResult doFindUserMenus() {
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		return new JsonResult(userService.findUserMenus(user.getId()));
	}
	
	@RequestMapping("/errorUI")
	public String errorUI() {
		return "error";
	}
	
}
