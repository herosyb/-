package com.tanzhou.tzms.product.service;

import java.util.List;

import com.tanzhou.tzms.product.domain.Team;

public interface TeamService {
	 public List<Team> findTeamAll();
	 public List<Team> findTeamByName(String name);
	 public Integer insertTeam(Team team);
	public List<Team> findTeamById(Integer id);
	public Integer updateTeam(Team team);
	public Integer updateStatusById(Integer status, String ids);
}
