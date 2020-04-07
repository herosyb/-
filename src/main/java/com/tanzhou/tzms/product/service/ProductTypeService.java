package com.tanzhou.tzms.product.service;

import java.util.List;
import java.util.Map;

import com.tanzhou.tzms.common.vo.Node;
import com.tanzhou.tzms.product.domain.ProductType;

public interface ProductTypeService {

	public List<Map<String,Object>> findAllProductType();
	
	public void deleteType(Integer id);
	
	public List<Node> findZtreeNode();
	
	public void saveProductType(ProductType pt);
}
