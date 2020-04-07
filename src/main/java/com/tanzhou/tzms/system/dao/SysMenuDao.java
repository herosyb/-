package com.tanzhou.tzms.system.dao;

import java.util.List;
import java.util.Map;

import com.tanzhou.tzms.common.dao.BaseDao;
import com.tanzhou.tzms.system.domain.SysMenu;


public interface SysMenuDao extends BaseDao<SysMenu> {
	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<Map<String,Object>> findObjects();
	/**
	 * 分页统计 条数
	 * @return
	 */
	public Integer getRowCount();
	/**
	 * 查询所有，用于展示选择菜单树结构
	 * @return
	 */
	public List<Map<String, Object>> findZtreeNodes();
	/**
	 * 查询要删除的菜单是否有子菜单
	 * @param menuId 菜单id
	 * @return
	 */
	public Integer hasChild(Integer menuId);
	/**
	 * 根据id查询菜单信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findMapById(Integer id);
	/**
	 * 删除菜单按钮
	 * @param id
	 * @return
	 */
	public Integer deleteObject(Integer id);

}
