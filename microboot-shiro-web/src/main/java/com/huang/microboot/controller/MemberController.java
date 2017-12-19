package com.huang.microboot.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MemberController {
	@RequestMapping("/loginPage")
	public String loginPage() {
		Subject subject=SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			return "/pages/welcome";
		}
		return "/pages/login_page";
	}
	@RequestMapping("/pages/hello")
	public String loginSuccessUrl() {
		return "/pages/welcome";
	}
}
