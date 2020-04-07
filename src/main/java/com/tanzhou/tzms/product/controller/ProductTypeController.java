package com.tanzhou.tzms.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tanzhou.tzms.common.vo.Node;
import com.tanzhou.tzms.common.web.JsonResult;
import com.tanzhou.tzms.product.domain.ProductType;
import com.tanzhou.tzms.product.service.ProductTypeService;

@Controller
@RequestMapping("/productType")
public class ProductTypeController {
	@Autowired
	private ProductTypeService typeService;
	/**
	 * 返回页面方法
	 * @return
	 */
	@RequestMapping("/listUI")
	public String listUI(){
		return "product/type_list";
	}
	/**
	 * 返回产品分类数据
	 * @return
	 */
	@RequestMapping("/findAllProductType")
	@ResponseBody
	public JsonResult findAllProductType(){
		List<Map<String, Object>> list = typeService.findAllProductType();
		return new JsonResult(list);
	}
	/**
	 * 删除产品分类
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteType")
	@ResponseBody
	public JsonResult deleteType(Integer id){
		typeService.deleteType(id);
		return new JsonResult("删除成功");
	}
	/**
	 * 显示编辑页面
	 * @return
	 */
	@RequestMapping("/editUI")
	public String editUI(){
		return "product/type_edit";
	}
	/**
	 * 查询树结构所需数据
	 * @return
	 */
	@RequestMapping("/findZtreeNodes")
	@ResponseBody
	public JsonResult findZtreeNodes(){
		List<Node> list = typeService.findZtreeNode();
		return new JsonResult(list);
	}
	
	@RequestMapping("/saveProductType")
	@ResponseBody
	public JsonResult saveProductType(ProductType pt){
		typeService.saveProductType(pt);
		return new JsonResult("新增成功");
	}
}
