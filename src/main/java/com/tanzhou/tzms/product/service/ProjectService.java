package com.tanzhou.tzms.product.service;

import java.util.List;
import java.util.Map;

import com.tanzhou.tzms.product.domain.Project;

/**
 * 项目管理业务层接口
 * @author xq
 *
 */
public interface ProjectService {

	public List<Project> findAllProject();
	
	
	public Map<String,Object> findPageObject(Integer pageCurrent,String name,Integer status);
	
	public Integer updateStatusById(Integer status,String ids);
	
	public void saveProject(Project project);
	
	public void updateProject(Project projec);

	public Project findProjectById(Integer id);
}
