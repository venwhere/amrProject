package com.sun.amr.action;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.IActionService;
@Controller
@RequestMapping("/pages/action/*")
public class ActionAction extends AbstractActionAdapter{
	@Resource
	private IActionService actionService;
	@RequestMapping("list")
	public ModelAndView list(int gid,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(6, request)) {
			try {
				mav.addObject("allActions",this.actionService.getAllByGroups(super.getEid(request), gid));
				//跳转到权限列表页面(/pages/action/action_list.jsp)
				mav.setViewName(super.getMsg("action.list.page"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
}


















