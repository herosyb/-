package com.tanzhou.tzms.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tanzhou.tzms.common.web.JsonResult;
import com.tanzhou.tzms.product.domain.Project;
import com.tanzhou.tzms.product.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	/**
	 * 返回项目管理页面
	 * @return
	 */
	@RequestMapping("/listUI")
	public String listUI(){
		return "product/project_list";//项目列表页面
	}
	
	/**
	 * 项目管理下的首页展示数据
	 */
	@RequestMapping("/findAllProject")
	@ResponseBody
	public List<Project> findAllProject(){
		List<Project> list = projectService.findAllProject();
		return list;//转成json [{"id":1,"name":"日本游"...},{}]
	}
	
	@RequestMapping("/findPageObject")
	@ResponseBody
	/*
	 {
	 	"list"：[{id:1,name:长沙游}]
	 	"pageObject":{pageCurrent:1,pageCount:2...}
	 }
	 */

/*public Map<String,Object> findPageObject(Integer pageCurrent,String name,Integer status){
		//String name1 = "长沙";
		//Integer status1 = 1;
		Map<String, Object> map = projectService.findPageObject(pageCurrent,name,status);
		return map;
	}*/
	/*
	 {
	 	state:1
	 	message:"OK"
	 	data: {
		 	"list"：[{id:1,name:长沙游}]
		 	"pageObject":{pageCurrent:1,pageCount:2...}
	 	}
	 }
	 */
	public JsonResult findPageObject(Integer pageCurrent,String name,Integer status){
		Map<String, Object> map = projectService.findPageObject(pageCurrent,name,status);
		return new JsonResult(map);
	}
	
	/*@RequestMapping("/updateStatusById")
	@ResponseBody
	public void updateStatusById(Integer status,String ids){
		projectService.updateStatusById(status, ids);
	}*/
	
	/*
	 
	正确： {
	 	state:1
	 	message:"启用成功"或者"禁用成功"
	 	data:
	 }
	 出现异常：
	   {
	 	state:0
	 	message:"状态值不合法"
	 	}
	 */
	@RequestMapping("/updateStatusById")
	@ResponseBody
	public JsonResult updateStatusById(Integer status,String ids){
		projectService.updateStatusById(status, ids);
		return new JsonResult(status==1?"启用成功":"禁用成功");
	}
	/*
	 name="湘潭游"&code="xxx"
	 
	 */
	@RequestMapping("/saveProject")
	@ResponseBody
	public JsonResult saveProject(Project project){
		//springmvc底层获取到参数数据，会对数据进行解析，
//		调用参数project对象的set方法将数据封装到project对象中
		projectService.saveProject(project);
		return new JsonResult("新增成功");
	}
	
	/**
	 * 返回项目的编辑页面
	 */
	@RequestMapping("/editUI")
	public String editUI(){
		return "product/project_edit";
	}
	/**
	 * 修改项目信息
	 * @param project
	 * @return
	 */
	@RequestMapping("/updateProject")
	@ResponseBody
	public JsonResult updateProject(Project project){
		projectService.updateProject(project);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("/findProjectById")
	@ResponseBody
	public JsonResult findProjectById(Integer id){
		Project project = projectService.findProjectById(id);
		return new JsonResult(project);
	}
	
}
