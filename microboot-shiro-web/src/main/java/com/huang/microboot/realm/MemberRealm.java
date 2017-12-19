package com.huang.microboot.realm;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.web.client.RestTemplate;
import com.huang.microboot.util.PasswordUtil;
import com.huang.microboot.vo.Member;

public class MemberRealm extends AuthorizingRealm{
	@Resource
	private RestTemplate restTemplate;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("==================1.进行认证处理===================");
		String mid=token.getPrincipal().toString();
		String url="http://localhost:8080/member/get?mid="+mid;
		Member member=this.restTemplate.postForObject(url,null,Member.class);
		if(member==null) {
			throw new UnknownAccountException("这个用户名不存在");
		}
		String password=PasswordUtil.getPassword(new String((char[])token.getCredentials()));
		if(!password.equals(member.getPassword())) {
			throw new IncorrectCredentialsException("密码错误，请重新输入！");
		}
		if(member.getLocked().equals(1)) {
			throw new LockedAccountException("该用户被锁了，请解锁！");
		}
		SimpleAuthenticationInfo auth=new SimpleAuthenticationInfo(token.getPrincipal(),
				password,"memberRealm");
		SecurityUtils.getSubject().getSession().setAttribute("name","是本人");
		return auth;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("+++++++++++++++++++2.尽心授权操作++++++++++++++++++++");
		SimpleAuthorizationInfo auth=new SimpleAuthorizationInfo();
		String mid=(String)principals.getPrimaryPrincipal();
		String url="http://localhost:8080/member/auth?mid="+mid;
		Map<String,Object> map=this.restTemplate.postForObject(url,null,Map.class);
		Set<String> allRoles=new HashSet<String>();
		Set<String> allActions=new HashSet<String>();
		allRoles.addAll((List<String>)map.get("allRoles"));
		allActions.addAll((List<String>)map.get("allActions"));
		auth.setRoles(allRoles);
		auth.setStringPermissions(allActions);
		return auth;
	}

}













