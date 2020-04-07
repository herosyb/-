package com.tanzhou.tzms.system.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuDao{
	/**
	 * 保存角色菜单关系
	 * @param roleId 角色id
	 * @param menuIds 菜单id
	 * @return
	 */
	public Integer insertRoleMenus(@Param("roleId")Integer roleId,@Param("menuIds")String[] menuIds);
	/**
	 * 删除关系表数据 ：根据roleid删除
	 * @param id
	 * @return
	 */
	public Integer deleteObject(Integer id);
	/**
	 * 根据roleId查询menuIdList
	 * @param id
	 * @return
	 */
	public List<Integer> findMenuIdsByRoleId(Integer id);
	
}
