package com.sun.amr.action;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.IAdminService;
import com.sun.amr.vo.Emp;
import com.sun.util.MD5Code;
@Controller
public class LoginAction extends AbstractActionAdapter{
	@Resource
	private IAdminService adminService;
	@RequestMapping("/pages/login")
	public String loginPage(Model model) {
		model.addAttribute("msg",super.getMsg("login.success"));
		model.addAttribute("url",super.getMsg("index.page"));
		return "/pages/forward";
	}
	@RequestMapping("/loginPage")  //进行认证结果的路劲选择
	public String login(String eid,String password,Model model) {
		//UsernamePasswordToken token=new UsernamePasswordToken(eid,password);
		Subject subject=SecurityUtils.getSubject();
		//subject.login(token);
		if(subject.isAuthenticated()) {
			model.addAttribute("msg",super.getMsg("login.success"));
			model.addAttribute("url",super.getMsg("index.page"));
		}else {
			model.addAttribute("msg",super.getMsg("login.failure"));
			model.addAttribute("url",super.getMsg("login.page"));
		}
		return "/pages/forward";
	}
	@RequestMapping("/logout")
	public ModelAndView logout(Emp vo,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		request.getSession().invalidate();
		super.setMsgAndUrl("logout.success", "login.page", mav);
		mav.setViewName(super.getMsg("forward.page"));
		return mav;
	}
}
