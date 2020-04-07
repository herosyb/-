package com.tanzhou.tzms.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzhou.tzms.common.exception.ServiceException;
import com.tanzhou.tzms.product.dao.TeamDao;
import com.tanzhou.tzms.product.domain.Team;
import com.tanzhou.tzms.product.service.TeamService;
@Service("teamService")
public class TeamServiceImpl implements TeamService{
	@Autowired
	private TeamDao teamDao;
	@Override
	public List<Team> findTeamAll() {
		// TODO Auto-generated method stub
		      return teamDao.findTeamAll();	
	}
	@Override
	public List<Team> findTeamByName(String name) {
		// TODO Auto-generated method stub
		return teamDao.findTeamByName(name);
		
	}
	@Override
	public Integer insertTeam(Team team) {
		// TODO Auto-generated method stub
		return teamDao.insertTeam(team);
		
	}
	@Override
	public List<Team> findTeamById(Integer id) {
		// TODO Auto-generated method stub
		return teamDao.findTeamById(id);		
	}
	@Override
	public Integer updateTeam(Team team) {
		// TODO Auto-generated method stub
		return teamDao.updateTeam(team);
		
	}
	@Override
	public Integer updateStatusById(Integer status, String ids){
		//ids:1,2,3--->数组
				if(ids==null||ids.equals("")){
					//抛出异常
					throw new ServiceException("ids的值不能为空");
				}
				//id:2
				String[] idarray = ids.split(",");
				if(status!=0&&status!=1){
					throw new ServiceException("状态值不合法");
				}
				return teamDao.updateStatusById(status, idarray);
			
	}
	
	
}
