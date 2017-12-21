package com.sun.amr.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.amr.vo.Emp;
@Controller
public class DoAuthAction {
	@RequestMapping("/")
	public String test() {
		return "/pages/login";
	}
	@RequestMapping("/pages/index")
	public String loginSuccess() {
		return "/pages/index";
	}
	@RequestMapping("/pages/unauthUrl")
	public String unAuthri() {
		return "/pages/unauthUrl";
	}
	
	@RequestMapping("/emp/test")
	@ResponseBody
	public Object testSession(HttpServletRequest request) {
		Session session=SecurityUtils.getSubject().getSession();
		return session.getAttribute("dept");
	}
}
