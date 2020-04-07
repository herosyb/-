package com.tanzhou.tzms.system.service;

import java.util.List;
import java.util.Map;

import com.tanzhou.tzms.common.vo.CurrentUser;

public interface SysService {

	
	//根据用户的用户名和密码进行认证，如果认证通过就返回用户信息
	public CurrentUser authenticat(String username,String password);
	
	//根据用户id查询权限范围内的菜单
	public List<Map<String,Object>> findMenuListByUserId(Integer uid);
}
