package com.tanzhou.tzms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzhou.tzms.common.exception.ServiceException;
import com.tanzhou.tzms.common.web.PageObject;
import com.tanzhou.tzms.product.dao.ProjectDao;
import com.tanzhou.tzms.product.domain.Project;
import com.tanzhou.tzms.product.service.ProjectService;
@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	private ProjectDao projectDao;
	
	
	@Override
	public List<Project> findAllProject() {
		return projectDao.findAllProject();
	}


	@Override
	public Map<String, Object> findPageObject(Integer pageCurrent,String name,Integer status) {//3
		//假设页面显示2条数据
		int pageSize = 5;//10  2  5  
		//第一页 1-2
		//第二页 3-4
		//第三页 5-6
		//第四页 7-8
		//第五页 9-10
		//2.获取分页信息
		int startIndex = (pageCurrent-1)*pageSize;
		//1.获取当前页数据
		List<Project> list = projectDao.findPageObject(startIndex, pageSize,name,status);
		
		//获取总条数
		int rowCount = projectDao.getRowCount(name,status);
		
		//计算总页数
//		int pageCount = rowCount/pageSize;//11/2=5  11/2 6
//		if(rowCount%pageSize!=0){
//			pageCount++;//6
//		}
		//封装分页信息
		PageObject page = new PageObject();
		page.setRowCount(rowCount);//总条数
		page.setPageSize(pageSize);//每页显示的条数
		page.setPageCurrent(pageCurrent);//选择的页数（用户传递的）
		page.setStartIndex(startIndex);//查询数据的开始
		
		//把当前数据以及分页信息封装到map中进行返回
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject", page);
		return map;
	}


	@Override
	public Integer updateStatusById(Integer status, String ids) {
		//ids:1,2,3--->数组
		if(ids==null||ids.equals("")){
			//抛出异常
			throw new ServiceException("ids的值不能为空");
		}
		//id:2
		String[] idarray = ids.split(",");
		if(status!=0&&status!=1){
			throw new ServiceException("状态值不合法");
		}
		return projectDao.updateStatusById(status, idarray);
	}


	@Override
	public void saveProject(Project project) {
		if(project==null){
			throw new ServiceException("保存对象不能为空");
		}
		Integer rows = projectDao.insertProject(project);
		if(rows<=0){//返回小于等于0，代表新增失败
			throw new ServiceException("新增失败");
		}
	}


	@Override
	public void updateProject(Project project) {
		if(project==null){
			throw new ServiceException("修改对象不能为空");
		}
		
		Integer rows = projectDao.updateProject(project);
		if(rows<=0){//返回小于等于0，代表修改失败
			throw new ServiceException("修改失败");
		}
	}


	@Override
	public Project findProjectById(Integer id) {
		if(id==null){
			throw new ServiceException("id不能为空");
		}
		Project project = projectDao.findProjectById(id);
		if(project==null){//没有查询出来数据
			throw new ServiceException("对象不存在");
		}
		//返回查询结果
		return project;
	}

}
