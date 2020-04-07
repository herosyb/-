package com.tanzhou.tzms.project;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tanzhou.tzms.product.domain.Project;
import com.tanzhou.tzms.product.service.ProjectService;

public class TestProjectService {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml","spring-mybatis.xml");
		//获取projectService
		ProjectService service = ac.getBean("projectService",ProjectService.class);
//		List<Project> list = bean.findAllProject();
//		if(list!=null && list.size()!=0){
//			for(Project p:list){
//				System.out.println(p);
//			}
//		}
		//当前数据库：3
		//每页查看两条数据
		//第一页：1-2
		//查看第二页：3
//		Map<String, Object> map = bean.findPageObject(1,"长",1);//
//		List<Project> list = (List<Project> )map.get("list");
//		System.out.println("当前页的数据"+list);
//		PageObject page = (PageObject)map.get("pageObject");
//		System.out.println("当前页："+page.getPageCurrent());
//	
		
//		Integer status = 1;
//		String ids = "1,2,3";
//		bean.updateStatusById(status, ids);
//		System.out.println("=================");
		
//		Project project = new Project();
//		project.setCode("tz-20191028-CN-BJ");
//		project.setName("湖南湘潭韶山");
//		project.setId(7);
//		project.setBeginDate(new Date());
//		project.setEndDate(new Date());
//		project.setStatus(1);
//		project.setRemarks("故居一日游....");
//		service.saveProject(project);
		
//		service.updateProject(project);
		
		
		Project project = service.findProjectById(7);
		System.out.println(project);
	}
}
