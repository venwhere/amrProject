package com.sun.amr.action;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.IDeptService;
import com.sun.amr.vo.Dept;
@Controller
@RequestMapping("/pages/dept/*")
public class DeptAction extends AbstractActionAdapter{
	@Resource
	private IDeptService deptService;
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if(super.isAuth(4, request)) {//验证用户的4号权限
			try {
				mav.addObject("allDepts",this.deptService.list(super.getEid(request)));
				//跳转到部门的列表页面(/pages/dept/dept_list.jsp)
				mav.setViewName(super.getMsg("dept.list.page"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			mav.setViewName(super.getMsg("error.page"));
		}
		return mav;
	}
	@RequestMapping("edit")
	public void edit(Dept vo,HttpServletRequest req,HttpServletResponse resp) {
		if(super.isAuth(1, req)) {
			try {
				//因为这是通过异步请求进行的调用，所以要使用response的print()进行响应
				super.print(resp, this.deptService.edit(super.getEid(req), vo));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			super.print(resp, "false");
		}
		
	}
}


















