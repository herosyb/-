package com.tanzhou.tzms.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tanzhou.tzms.common.dao.BaseDao;
import com.tanzhou.tzms.system.domain.SysUser;

public interface SysUserDao extends BaseDao<SysUser> {
	/**
	 * 查询用户列表 
	 * @param username
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<SysUser> findPageObjects(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize") Integer pageSize);
	/**
	 * 查询记录总条数
	 * @param username
	 * @return
	 */
	public Integer getRowCount(@Param("username")String username);
	/**
	 * 根据id查询用户信息 
	 * @param id
	 * @return
	 */
	public SysUser findObjectById(Integer id);
	/**
	 * 切换用户启用/禁用状态
	 * @param id
	 * @param valid
	 * @return
	 */
	public Integer validById(@Param("id")Integer id,@Param("valid")Integer valid);
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	public	SysUser findObjectByName(String username);
	/**
	 * 查询用户得权限
	 * @param userId
	 */
	List<String> findUserPermissions(Integer userId);
	/**
	 * 查询登陆用户可见的所有菜单
	 * @param userId
	 * @return
	 */
	List<Map<String,Object>> findUserMenus(Integer userId);
	
	

}
