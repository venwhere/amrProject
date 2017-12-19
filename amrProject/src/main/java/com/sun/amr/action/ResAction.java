package com.sun.amr.action;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.IResService;
import com.sun.util.SplitUtil;
@Controller
@RequestMapping("/pages/res/*")
public class ResAction extends AbstractActionAdapter{
	@Resource
	private IResService resService;

	@RequestMapping("/list")
	public ModelAndView listRes(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		//创建分页工具类
		SplitUtil split=new SplitUtil(this);
		//将查询信息交给工具处理
		super.handleSplit(request, split);
		try {
			//调用业务层的方法取得数据
			Map<String, Object> map=this.resService.list(split.getColumn(), split.getKeyword(),
					split.getCurrentPage(), split.getLineSize());
			//该路径为分页中的“上一页”和“下一页”调用的
			mav.addObject("url",super.getMsg("res.list.action"));
			//保存用品信息
			mav.addObject("allRess",map.get("allRess"));
			//分页信息
			mav.addObject("pager",map.get("pager"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//跳转到办公用品列表页面
		mav.setViewName(super.getMsg("res.list.page"));
		return mav;
	}
	/**
	 * 指定搜索是提供的字段
	 */
	@Override
	public String getColumnData() {
		return "标题:title|编号:rid";
	}
	/**
	 * 指定默认的模糊查询的字段
	 */
	@Override
	public String getDefaultColumn() {
		return "title";
	}
}

















