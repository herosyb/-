package com.tanzhou.tzms.product.dao;

import java.util.List;
import java.util.Map;

import com.tanzhou.tzms.common.vo.Node;
import com.tanzhou.tzms.product.domain.ProductType;

/**
 * 产品分类dao接口
 * @author xq
 *
 */
public interface ProductTypeDao {
	/**
	 * 查询所有产品分类
	 * @return
	 */
	public List<Map<String,Object>> findAllProductType();
	//父id:1  父名字：境内游
	/**
	 * 根据id删除数据
	 * @param id
	 * @return
	 */
	public Integer deleteType(Integer id);
	/**
	 * 根据id查询是否有子
	 * @param id
	 * @return
	 */
	public Integer hasChildType(Integer id);
	
	/**
	 * 获取Ztree需要分类数据:id,父id,名字
	 * vo:value object
	 */
	public List<Node> findZtreeNode();
	/**
	 * 新增产品分类
	 * @param pt
	 * @return
	 */
	public Integer insertProductType(ProductType pt);
}
