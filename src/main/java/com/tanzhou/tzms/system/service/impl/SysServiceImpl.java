package com.tanzhou.tzms.system.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.rowset.serial.SerialException;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzhou.tzms.common.exception.ServiceException;
import com.tanzhou.tzms.common.vo.CurrentUser;
import com.tanzhou.tzms.system.dao.SysUserDao;
import com.tanzhou.tzms.system.domain.SysUser;
import com.tanzhou.tzms.system.service.SysService;

@Service("sysService")
public class SysServiceImpl implements SysService{
	@Autowired
	private SysUserDao userDao;
	
	@Override
	public CurrentUser authenticat(String username, String password) {
		//根据用户名去数据库查询是否存在当前用户
		SysUser sysuser = userDao.findObjectByName(username);
		if(sysuser==null){
			throw new ServiceException("当前用户不存在！！请联系管理员");
		}
		//当前用户的密码
		String pw = sysuser.getPassword();
		
		//盐
		String saltStr = sysuser.getSalt();
		ByteSource salt = ByteSource.Util.bytes(saltStr);
		//md5加密字符串
		String pwd = new SimpleHash("MD5",password,salt).toString();
		if(!pw.equals(pwd)){
			throw new ServiceException("用户名或密码不正确！！");
		}
		//获取当前用户的权限菜单
		List<Map<String, Object>> list = this.findMenuListByUserId(sysuser.getId());
		//代表用户名和密码都对了
		CurrentUser user = new CurrentUser();
		user.setUserId(sysuser.getId());
		user.setUsername(sysuser.getUsername());
		user.setMenus(list);
		return user;
	}

	@Override
	public List<Map<String, Object>> findMenuListByUserId(Integer uid) {
		List<Map<String, Object>> list = userDao.findUserMenus(uid);
		return list;
	}

}
