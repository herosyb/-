package com.tanzhou.tzms.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzhou.tzms.common.exception.ServiceException;
import com.tanzhou.tzms.common.vo.Node;
import com.tanzhou.tzms.product.dao.ProductTypeDao;
import com.tanzhou.tzms.product.domain.ProductType;
import com.tanzhou.tzms.product.service.ProductTypeService;

@Service("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService{
	

	@Autowired
	private ProductTypeDao typeDao;
	
	@Override
	public List<Map<String, Object>> findAllProductType() {
		List<Map<String, Object>> list = typeDao.findAllProductType();
		return list;
	}

	@Override
	public void deleteType(Integer id) {
		if(id==null || id<=0){
			throw new ServiceException("id值无效：id="+id);
		}
		//查询当前id下对应的分类是否有子
		Integer count = typeDao.hasChildType(id);
		if(count>0){//代表有子元素
			throw new ServiceException("有子元素，不允许删除");
		}
		//没有子，正常删除
		Integer row = typeDao.deleteType(id);
		if(row<1){
			throw new ServiceException("删除失败");
		}
		
	}

	@Override
	public List<Node> findZtreeNode() {
		List<Node> list = typeDao.findZtreeNode();
		return list;
	}

	@Override
	public void saveProductType(ProductType pt) {
		if(pt==null){
			throw new ServiceException("保存对象不能为空");
		}
		Integer rows = typeDao.insertProductType(pt);
		if(rows<1){
			throw new ServiceException("新增失败");
		}
	}

}
