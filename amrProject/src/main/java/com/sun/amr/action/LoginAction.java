package com.sun.amr.action;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
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
	@RequestMapping("/login")
	public ModelAndView login(Emp emp,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		String password=request.getParameter("password");
		int eid=Integer.parseInt(request.getParameter("eid"));
		//将用户输入的密码进行加密处理后再进行查询
		emp.setPassword(new MD5Code().getMD5ofStr(password));
		emp.setEid(eid);
		try {
			if(this.adminService.login(emp)) {
				request.getSession().setAttribute("emp", emp);
				//设置跳转路径信息和提示信息
				super.setMsgAndUrl("login.success","index.page",mav);
			}else {//登陆失败后跳转到登陆页面继续登陆
				super.setMsgAndUrl("login.failure", "login.page", mav);
			}
			mav.setViewName(super.getMsg("forward.page"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
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
