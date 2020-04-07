package com.tanzhou.tzms.system.service;

import java.util.List;
import java.util.Map;

import com.tanzhou.tzms.system.domain.SysMenu;

public interface SysMenuService {

	public List<Map<String, Object>> findObjects();
	
	public List<Map<String, Object>> findZtreeNodes();
	
	public void saveObject(SysMenu entity);
	
	public Map<String, Object> findMapById(Integer menuId);
	
	public void updateObject(SysMenu entity);
	
	public void deleteObject(Integer menuId);

}
