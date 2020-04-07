package com.tanzhou.tzms.system.service;

import java.util.List;
import java.util.Map;

import com.tanzhou.tzms.system.domain.SysUser;

public interface SysUserService {
	
	public Map<String, Object> findPageObjects(String username, Integer currentPage);
	
	public void saveObject(SysUser user,String roleIds);
	
	public Map<String,Object> findUserById(Integer userId);
	
	public void updateObject(SysUser user,String roleIds);
	
	public void validById(Integer userId, Integer valid);
	
	public List<String> findUserPermissions(Integer userId);
	
	public List<Map<String,Object>> findUserMenus(Integer userId);
	
	public List<Map<String, Object>> findSysRoles();
	
	

}
