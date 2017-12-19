package com.sun.amr.action;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.ILevelService;
@Controller
@RequestMapping("/pages/level/*")
public class LevelAction extends AbstractActionAdapter{
	@Resource
	private ILevelService levelService;
	@RequestMapping("checkSalary")//这个访问路径是异步请求，所以这里可以返回为void
	private void checkSalary(int lid,double salary,HttpServletResponse response) {
		try {
			super.print(response,this.levelService.checkSalary(salary, lid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



















