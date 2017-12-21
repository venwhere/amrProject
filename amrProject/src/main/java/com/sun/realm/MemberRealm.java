package com.sun.realm;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;

import com.sun.util.PasswordUtil;
import com.sun.amr.service.impl.AdminServiceImpl;
import com.sun.amr.vo.Emp;
public class MemberRealm extends AuthorizingRealm{
	@Resource
	private AdminServiceImpl adminServiceImpl;
	@Resource
	private RedisTemplate<String,Object> redisTemplate;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.err.println("==================1.进行认证处理===================");
		Integer eid=Integer.parseInt(token.getPrincipal().toString());
		Emp vo=new Emp();
		vo.setEid(eid);
		Emp emp=null;
		try {
			emp = this.adminServiceImpl.login(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(emp==null) {
			throw new UnknownAccountException("这个用户名不存在");
		}
		String password=PasswordUtil.getPassword(eid.toString(),new String((char[])token.getCredentials()));
		if(!password.equals(emp.getPassword())) {
			throw new IncorrectCredentialsException("密码错误，请重新输入！");
		}
		SimpleAuthenticationInfo auth=new SimpleAuthenticationInfo(token.getPrincipal(),
				password,"memberRealm");
		SecurityUtils.getSubject().getSession().setAttribute("dept", emp.getDept());
		return auth;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.err.println("+++++++++++++++++++2.进行授权操作++++++++++++++++++++");
		SimpleAuthorizationInfo auth=new SimpleAuthorizationInfo();
		Integer eid=Integer.parseInt(principals.getPrimaryPrincipal().toString());
		Emp vo=new Emp();
		vo.setEid(eid);
		Emp emp=null;
		try {
			emp = this.adminServiceImpl.login(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map=null;
		try {
			map = this.adminServiceImpl.auth(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		auth.setRoles((Set<String>) map.get("allRoles"));
		auth.setStringPermissions((Set<String>) map.get("allActions"));
		return auth;
	}

}













