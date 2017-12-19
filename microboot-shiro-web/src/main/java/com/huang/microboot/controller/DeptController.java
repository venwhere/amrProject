package com.huang.microboot.controller;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.huang.microboot.vo.Dept;
@Controller
public class DeptController {
	@RequestMapping("/pages/back/list")
	@ResponseBody
	@RequiresRoles("dept")
	public Object getDept() {
		Dept dept=new Dept();
		dept.setDeptno("10");
		dept.setTitle("研发部门");
		return dept;
	}
	@RequestMapping("/pages/unauthUrl")
	public Object getUnauthc() {
		return "pages/error";
	}
	@RequestMapping("/pages/back/show")
	public String show() {
		return "/pages/welcome";
	}
}
