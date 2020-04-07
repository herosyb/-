package com.tanzhou.tzms.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tanzhou.tzms.common.web.JsonResult;
import com.tanzhou.tzms.product.domain.Project;
import com.tanzhou.tzms.product.domain.Team;
import com.tanzhou.tzms.product.service.TeamService;
@Controller
@RequestMapping("/team")
public class TeamController {
 @Autowired
 private TeamService teamService;
 @RequestMapping("/listUI")
	public String listUI(){
		return "product/team_list";//项目列表页面
	}
 @RequestMapping("/edit")
	public String edit(){
		return "product/team_edit";
}
 @RequestMapping("/findTeamAll")
	@ResponseBody
	public List<Team> findAllProject(){
		List<Team> list = teamService.findTeamAll();
		return list;//转成json [{"id":1,"name":"日本游"...},{}]
	}
 @RequestMapping("/findTeamByName")
     @ResponseBody
	public List<Team> findTeamByName(String name){
	 List<Team> list = teamService.findTeamByName(name);
	 return list;
	}
 @RequestMapping("/saveTeam")
 @ResponseBody
public JsonResult saveTeam(Team team){
	  teamService.insertTeam(team);
	  return new JsonResult("新增成功");
}
 @RequestMapping("/findTeamById")
 @ResponseBody
public JsonResult findTeamById(Integer id){
 List<Team> team = teamService.findTeamById(id);
 return new JsonResult(team);
 }
 @RequestMapping("/updateTeam")
 @ResponseBody
public JsonResult updateTeam(Team team){
   teamService.updateTeam(team);
  return new JsonResult("修改成功");
 }
 @RequestMapping("/updateStatusById")
 @ResponseBody
public JsonResult updateStatusById(Integer status,String ids){
   teamService.updateStatusById(status , ids);
   return new JsonResult(status==1?"启用成功":"禁用成功");
 }
}
