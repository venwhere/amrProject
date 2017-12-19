package com.sun.amr.action;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.IAdminService;
import com.sun.amr.vo.Dept;
import com.sun.amr.vo.Emp;
import com.sun.amr.vo.Level;
import com.sun.util.MD5Code;
import com.sun.util.SplitUtil;
@Controller
@RequestMapping("pages/admin/*")
public class AdminAction extends AbstractActionAdapter {
	@Resource
	private IAdminService adminService;
	@RequestMapping("checkEid")
	public void checkEid(Integer eid,HttpServletResponse response) {
		try {
			super.print(response, this.adminService.checkEid(eid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("addPre")
	public ModelAndView addPre(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (super.isAuth(1, request)) {// 对权限1进行验证
			try {
				Map<String, Object> map = this.adminService.addPre();
				// 保存的是雇员的级别信息
				mav.addObject("allLevels", map.get("allLevels"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 跳转到/pages/admin/admin_add.jsp
			mav.setViewName(super.getMsg("admin.add.page"));
		}else {
			//如果没有权限跳转到错误页面
			mav.setViewName(super.getMsg("errors"));
		}
		return mav;
	}
	@RequestMapping("add")
	public ModelAndView add(Emp vo,MultipartFile pic,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if(super.isAuth(2, request)) {//验证当前用户是否有权限
			//spring-mvc可以自动赋值不用这样赋值
			vo.setDept(new Dept());
			vo.getDept().setDid(1);//指定雇员所在部门为1号部门
			vo.setLevel(new Level());
			vo.getLevel().setLid(Integer.parseInt(request.getParameter("level.lid")));
			vo.setHeid(super.getEid(request));//设置操作人员的编号
			vo.setPassword(new MD5Code().getMD5ofStr(vo.getPassword()));//密码的加密处理
			vo.setAflag(2);
			vo.setPhoto(super.createSingleFileName(pic));//图片的名称需要保存到数据库
			System.out.println(pic);
			System.out.println(vo.getPhoto());
			try {
				if(this.adminService.add(vo, super.getEid(request))) {//调用业务层的方法进行数据的增加操作
					super.saveUploadFile(pic, vo.getPhoto(), request);//添加成功则将照片保存到服务器
					super.setMsgAndUrl("vo.add.success", "admin.add.action", mav);//定义提示信息和跳转路径
				}else {
					super.setMsgAndUrl("vo.add.failure", "admin.add.action", mav);
				}
			} catch (Exception e) {
				super.setMsgAndUrl("vo.add.failure", "admin.add.action", mav);
				e.printStackTrace();
			}
			mav.setViewName(super.getMsg("forward.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		if(super.isAuth(3, request)) {//权限验证
			//创建一个分页参数处理的工具类，this表示当前的对象，传递这个的目的是取得子类中给出一些参数
			SplitUtil split=new SplitUtil(this);
			//调用父类的方法进行客户端传递参数的处理，request负责在父类中取得参数，split在父类中处理参数
			super.handleSplit(request, split);
			try {
				Map<String, Object> map=this.adminService.list(super.getEid(request),
						split.getColumn(),split.getKeyword(),
						split.getCurrentPage(), split.getLineSize());
				//保存的是查看管理员的路径，需要在分页的时候使用到（比如上一页）
				map.put("url", super.getMsg("admin.list.action"));
				super.setPageMsg(map, mav);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//跳转到管理员的列表页面（/pages/admin/admin_list.jsp）
			mav.setViewName(super.getMsg("admin.list.page"));
		}else {
			mav.setViewName(super.getMsg("error.page"));
		}
		return mav;
	}
	@Override
	public String getColumnData() {
		return "雇员姓名:name|雇员编号:eid|雇员电话:phone";
	}
	@Override
	public String getFlag() {
		return "管理员";
	}
	@Override
	public String getSaveFileDiv() {
		return "/upload/emp/";
	}
	@Override
	public String getDefaultColumn() {
		return "name";
	}
}





















