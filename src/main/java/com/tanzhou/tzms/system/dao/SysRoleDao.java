package com.tanzhou.tzms.system.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tanzhou.tzms.common.dao.BaseDao;
import com.tanzhou.tzms.system.domain.SysRole;

public interface SysRoleDao extends BaseDao<SysRole> {
	/**
	 * 获取所有角色信息
	 * @return
	 */
	public List<Map<String, Object>> findObjects();
	/**
	 * 分页获取所有角色信息
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<SysRole> findPageObjects(@Param("name")String name, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);
	/**
	 * 根据id获取角色信息
	 * @param id
	 * @return
	 */
	public SysRole findObjectById(Integer id);
	/**
	 * 根据参数获取名字记录数
	 * @param name
	 * @return
	 */
	public int getRowCounts(@Param("name") String name);
	/**
	 * 根据id删除对象信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);

}
