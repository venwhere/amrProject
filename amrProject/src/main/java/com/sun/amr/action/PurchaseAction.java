package com.sun.amr.action;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.IPurchaseService;
import com.sun.amr.vo.Purchase;
import com.sun.util.SplitUtil;
@Controller
@RequestMapping("/pages/purchase/*")
public class PurchaseAction extends AbstractActionAdapter{
	@Resource
	private IPurchaseService purchaseService;
	/**
	 * 实现申请清单的提交
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(Purchase vo,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(30, request)) {
			try {
				if (this.purchaseService.add(super.getEid(request), vo)) {
					super.setMsgAndUrl("vo.add.success", "purchase.apply.action", mav);
				}else {
					super.setMsgAndUrl("vo.add.failure", "purchase.apply.action", mav);
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.add.failure", "purchase.apply.action", mav);
			}
			mav.setViewName(super.getMsg("forward.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	/**
	 * 实现申请单信息列表的显示
	 * @param request
	 * @return
	 */
	@RequestMapping("apply")
	public ModelAndView apply(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(27, request)) {//验证权限
			//处理用户提交的分页有关的参数对象
			SplitUtil split=new SplitUtil(this);
			//将用户提交的参数交给split处理
			super.handleSplit(request, split);
			try {
				//调用业务层的方法查询数据，数据保存到map集合中
				Map<String, Object> map=this.purchaseService.listByEmp(super.getEid(request), split.getCurrentPage(),
						split.getLineSize());
				//将map中的数据取出保存到ModelAndView中（request属性范围）
				mav.addObject("allPurchases",map.get("allPurchases"));
				//保存的是当前方法的路径，目的是为了上一页和下一页提供路径
				mav.addObject("url",super.getMsg("purchase.apply.action"));
				mav.addObject("pager",map.get("pager"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//跳转页面:/pages/purchase/purchase_apply.jsp
			mav.setViewName(super.getMsg("purchase.apply.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	/**
	 * 申请人查询申请变详情
	 * @param pid
	 * @param request
	 * @return
	 */
	@RequestMapping("show")
	public ModelAndView show(int pid,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(27, request)) {
			try {
				//将查询到的信息保存到request属相范围
				mav.addObject("purchase",this.purchaseService.show(super.getEid(request), pid,27));
			} catch (Exception e) {
				e.printStackTrace();
			}
			mav.setViewName(super.getMsg("purchase.show.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	/**
	 * 为财务部门查询申请单的信息
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(41, request)) {//需要41号权限
			SplitUtil split=new SplitUtil(this);//创建处理分页参数的工具类对象
			super.handleSplit(request, split);  //将蚕食交给工具类
			try {
				//查询申请单数据
				Map<String, Object> map=this.purchaseService.listSimple(super.getEid(request), 
						split.getCurrentPage(), split.getLineSize());
				mav.addObject("allPurchases",map.get("allPurchases"));
				mav.addObject("url",super.getMsg("purchase.list.action"));
				mav.addObject("pager",map.get("pager"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//跳转申请单列表页面（/pages/purchase/purchase_list.jsp）
			mav.setViewName(super.getMsg("purchase.list.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	/**
	 * 财务部门进行申请单的详细信息查询
	 * @param pid  申请单编号
	 * @param request
	 * @return
	 */
	@RequestMapping("show5")
	public ModelAndView show5(int pid,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(41, request)) {
			try {
				//将查询到的信息保存到request属相范围
				mav.addObject("purchase",this.purchaseService.show(super.getEid(request), pid,41));
			} catch (Exception e) {
				e.printStackTrace();
			}
			mav.setViewName(super.getMsg("purchase.show.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	/**
	 * 实现申请单的审核操作，该方法是异步请求调用的
	 * @param pid  申请单编号
	 * @param status  审核
	 * @param request
	 * @param response
	 */
	@RequestMapping("audit")
	public void aduit(int pid,int status,HttpServletRequest request,
			HttpServletResponse response) {
		if (super.isAuth(42, request)) {
			try {
				super.print(response,this.purchaseService.editStatus(super.getEid(request), pid, status));
			} catch (Exception e) {
				e.printStackTrace();
				super.print(response, "false");
			}
		}else {
			super.print(response, "false");
		}
	}
}






















