package com.tanzhou.tzms.system.dao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDao {
	/**
	 * 判断是否有用户占用此角色
	 * @param roleId
	 * @return
	 */
	public Integer isUsedByUser(@Param("roleId")Integer roleId);
	/**
	 * 保存用户角色关系
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	public Integer insertObject(@Param("userId")Integer userId,@Param("roleIds")String[] roleIds);
	/**
	 *删除用户角色关系
	 * @param userId
	 * @return
	 */
	public Integer deleteUserRoles(Integer userId);
	/**
	 * 查询用户的所有角色id
	 * @param userId
	 * @return
	 */
	public List<Integer> findRoleIdsByUserId(Integer userId);
}
