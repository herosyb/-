package com.tanzhou.tzms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tanzhou.tzms.product.domain.Team;

public interface TeamDao {
    public List<Team> findTeamAll();
    public List<Team> findTeamByName(@Param("name")String name);
	public Integer insertTeam(Team team);
	public List<Team> findTeamById(@Param("id")Integer id);
	public Integer updateTeam(Team team);
	public Integer updateStatusById(@Param("status")Integer status, @Param("ids")String[] ids);
}
