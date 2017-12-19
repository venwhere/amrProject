package com.sun.amr.action;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.IGroupsService;
@Controller
@RequestMapping("/pages/groups/*")
public class GroupsAction extends AbstractActionAdapter{
	@Resource
	private IGroupsService groupsService;
	@RequestMapping("list")
	public ModelAndView list(int did,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(6, request)) {
			try {
				mav.addObject("allGroups",this.groupsService.getAllByDept(super.getEid(request), did));
				//跳转到权限组列表页面
				mav.setViewName(super.getMsg("groups.list.page"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
}



















