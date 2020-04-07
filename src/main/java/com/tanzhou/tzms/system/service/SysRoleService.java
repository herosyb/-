package com.tanzhou.tzms.system.service;

import java.util.List;
import java.util.Map;

import com.tanzhou.tzms.system.domain.SysRole;


public interface SysRoleService {

	public Map<String, Object> findObjects(String name, Integer pageCurrent);
	
	public void saveObject(SysRole role,String menuIdList);
	
	public Map<String,Object> findMapById(Integer roleId);
	
	public void updateRole(SysRole role,String menuIdList);
	
	public void deleteObject(Integer roleId);
	
	public List<Map<String, Object>> findZtreeNodes();

}
