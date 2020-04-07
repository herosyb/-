package com.tanzhou.tzms.common.vo;

import java.util.List;
import java.util.Map;

public class CurrentUser {

	private Integer userId;
	private String username;
	
	private List<Map<String,Object>> menus;//菜单 名字，url
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<Map<String, Object>> getMenus() {
		return menus;
	}
	public void setMenus(List<Map<String, Object>> menus) {
		this.menus = menus;
	}
	@Override
	public String toString() {
		return "CurrentUser [userId=" + userId + ", username=" + username + "]";
	}
	
	
}
