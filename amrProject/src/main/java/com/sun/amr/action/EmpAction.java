package com.sun.amr.action;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.IAdminService;
import com.sun.amr.service.IEmpService;
import com.sun.amr.vo.Emp;
import com.sun.util.MD5Code;
import com.sun.util.SplitUtil;
@Controller
@RequestMapping("/pages/emp/*")
public class EmpAction extends AbstractActionAdapter{
	@Resource
	private IAdminService adminService;
	@Resource
	private IEmpService empService;
	@RequestMapping("addPre")
	public ModelAndView addPre(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(12, request)) {
			try {
				Map<String,Object> map=this.empService.addPre();
				mav.addObject("allLevels",map.get("allLevels"));
				mav.addObject("allDepts",map.get("allDepts"));
//				mav.addObject("url",super.getMsg("emp.list.action"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			mav.setViewName(super.getMsg("emp.add.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;	
	}
	@RequestMapping("add")
	public ModelAndView add(Emp vo,MultipartFile pic,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(12, request)) {
			vo.setPhoto(super.createSingleFileName(pic));//创建新的文件名
			vo.setHeid(super.getEid(request));  //设置操作人的编号
			vo.setPassword(new MD5Code().getMD5ofStr(vo.getPassword()));  //对密码进行加密
			mav.setViewName(super.getMsg("forward.page"));
			try {
				if (this.empService.add(vo, super.getEid(request))) {//如果增加成功则将照片上传到服务器
					super.saveUploadFile(pic, vo.getPhoto(), request);
					//设置在forward.jsp中的提示信息和跳转路径
					super.setMsgAndUrl("vo.add.success", "emp.add.action", mav);
				}
			} catch (Exception e) {
				//设置在forward.jsp中的提示信息和跳转路径
				super.setMsgAndUrl("vo.add.failure", "emp.add.action", mav);
				e.printStackTrace();
			}
		}else {
			//设置跳转路劲
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(13, request)) {//权限验证
			SplitUtil split=new SplitUtil(this);//创建分页处理的工具类
			super.handleSplit(request, split);  //将客户端的传递的信息传给工具类处理
			try {
				//将工具类处理完的参数传递给业务层方法查询员工信息
				Map<String, Object> map=this.empService.list(super.getEid(request), 
						split.getColumn(), split.getKeyword(), 
						split.getCurrentPage(), split.getLineSize());
				//将查询雇员列表信息的路径（访问本方法的路径），目的是为分页和搜索提供路径
				map.put("url", super.getMsg("emp.list.action"));
				super.setPageMsg(map, mav);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//设置跳转路径
			mav.setViewName(super.getMsg("emp.list.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	/**
	 * 人事部门页面编辑员工信息
	 * @param eid  需要编辑的员工编号
	 * @param request
	 * @return
	 */
	@RequestMapping("editPre")
	public ModelAndView editPre(int eid,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(14, request)) {
			Map<String, Object> map;
			try {
				map = this.empService.eidtPre(eid);
				mav.addObject("allLevels",map.get("allLevels"));
				mav.addObject("allDepts",map.get("allDepts"));
				mav.addObject("editEmp",map.get("emp"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			mav.setViewName(super.getMsg("emp.edit.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	@RequestMapping("edit")
	public ModelAndView edit(Emp vo,MultipartFile pic,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		System.out.println(vo.getPhoto());
		if (super.isAuth(15, request)) {
			if (pic!=null&&pic.getSize()>0) {  //创建新图片的名称
				vo.setPhoto(super.createSingleFileName(pic));
			}
			System.out.println(vo.getPhoto());
			vo.setHeid(super.getEid(request));//取得该员工原来的添加人
			if (vo.getPassword()==null||"".equals(vo.getPassword())) {//如果不需要修改密码则设置为null
				vo.setPassword(null);
			}else {
				vo.setPassword(new MD5Code().getMD5ofStr(vo.getPassword()));//将新密码加密
			}
			//跳转到forward.jsp页面
			mav.setViewName(super.getMsg("forward.page"));
			try {
				if (this.empService.edit(vo, super.getEid(request))) {
					super.saveUploadFile(pic, vo.getPhoto(), request);  //保存文件
					super.setMsgAndUrl("vo.edit.success", "emp.list.action", mav);
				}else {
					super.setMsgAndUrl("vo.edit.failure", "emp.list.action", mav);
				}
			} catch (Exception e) {
				super.setMsgAndUrl("vo.edit.failure", "emp.list.action", mav);
				e.printStackTrace();
			}
		}else {//如果没有权限则跳转到错误页面
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	@Override
	public String getColumnData() {
		return "雇员姓名:name|雇员编号:eid|雇员电话:phone";
	}
	@Override
	public String getFlag() {
		return "雇员";
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




















