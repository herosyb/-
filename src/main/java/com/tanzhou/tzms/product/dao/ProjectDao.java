package com.tanzhou.tzms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tanzhou.tzms.product.domain.Project;

/**
 * 项目模块的dao层
 * @author xq
 *
 */
public interface ProjectDao {

	/**
	 * 查询所有的项目信息
	 */
	public List<Project> findAllProject();
	/**
	 * 分页查询项目数据
	 * @param startIndex：分页查询时起始位置
	 * @param pageSize：每页显示的条数
	 * @return
	 * 
	 */
	public List<Project> findPageObject(
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize,
			@Param("name")String name,
			@Param("status")Integer status
			);
	
	
	/**
	 * 查询项目的总条数
	 * @return
	 */
	public Integer getRowCount(
			@Param("name")String name,
			@Param("status")Integer status);
	/**
	 * 禁用或启用项目信息
	 * @param status 状态值
	 * @param ids id值
	 * @return
	 */
	public Integer updateStatusById(@Param("status")Integer status,@Param("ids")String[] ids);
	
	/**
	 * 添加项目
	 * @return
	 */
	public Integer insertProject(Project project);
	/**
	 * 修改项目
	 * @param project
	 * @return
	 */
	public Integer updateProject(Project project);
	
	/**
	 * 根据项目id查询项目信息
	 * @param id
	 * @return
	 */
	public Project findProjectById(Integer id);
	
	
	
	
}
