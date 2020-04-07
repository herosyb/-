package com.tanzhou.tzms.common.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.tanzhou.tzms.common.vo.CurrentUser;
import com.tanzhou.tzms.system.dao.SysUserDao;
import com.tanzhou.tzms.system.domain.SysUser;
import com.tanzhou.tzms.system.service.SysService;

public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	private SysService sysService;
	
	@Autowired
	private SysUserDao userDao;
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
//		//从参数中获取主身份信息(从doGetAuthenticationInfo认证方法中通过SimpleAuthenticationInfo返回的主身份信息)
////		String username = (String)principal.getPrimaryPrincipal();
//		CurrentUser user = (CurrentUser)principal.getPrimaryPrincipal();
////		System.out.println(username);
//		
//		//根据身份信息获取权限信息
//		//从数据库中查询
//		
//		//一个用户所对应的权限信息是有多个
//		List<String> list = new ArrayList<String>();
//		
//		//模拟从数据库获取的
//		list.add("user:create");
//		list.add("sys:menu:view");
//		SimpleAuthorizationInfo sim = new SimpleAuthorizationInfo();
//		//将查询到的权限信息添加到对象中
//		sim.addStringPermissions(list);
		
//		return sim;
		
		CurrentUser user = (CurrentUser)principal.getPrimaryPrincipal();
		//根据身份信息获取权限信息
		List<String> list = userDao.findUserPermissions(user.getUserId());
		Set<String> set = new HashSet<String>();
		for(String perm : list){
			if(perm!=null && !("".equals(perm))){
				set.add(perm);
			}
		}
		SimpleAuthorizationInfo sim = new SimpleAuthorizationInfo();
		sim.addStringPermissions(set);
		return sim;
	}
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.从token参数中取出用户身份信息（用户输入的）
//		String username = (String)token.getPrincipal();//用户名
//		//System.out.println(username);
//		//2.根据用户输入的username从数据库中查询 
//		//模拟从数据库查询
//		//如果查询不到，则返回null
//		
//		//如果查询到了，则获取当前用户对应的密码
//		String password = "123456";
//		
//		
//		CurrentUser user = new CurrentUser();
//		user.setUserId(2);
//		user.setUsername("coco");
//		
//		List<Map<String, Object>> menuList = sysService.findMenuListByUserId(user.getUserId());
//		user.setMenus(menuList);
//		
//		SimpleAuthenticationInfo sim = new SimpleAuthenticationInfo
//				(user, password, "c");
//		return sim;
		String username = (String)token.getPrincipal();//用户名
		//根据用户名去数据库查询是否存在当前用户
		SysUser sysuser = userDao.findObjectByName(username);
		if(sysuser==null){
			return null;
		}
		//当前用户的密码
		String pw = sysuser.getPassword();//加密后的密码
		
		//盐
		String saltStr = sysuser.getSalt();//盐值
		ByteSource salt = ByteSource.Util.bytes(saltStr);
		
		CurrentUser user = new CurrentUser();
		user.setUserId(sysuser.getId());
		user.setUsername(sysuser.getUsername());
		List<Map<String, Object>> menuList = sysService.findMenuListByUserId(user.getUserId());
		user.setMenus(menuList);
		
		
		SimpleAuthenticationInfo sim =
					new SimpleAuthenticationInfo(user, pw, salt, "c");
				
		return sim;
	}
	
	

}
