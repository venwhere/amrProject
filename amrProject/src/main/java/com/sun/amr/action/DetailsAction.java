package com.sun.amr.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.IDetailsService;
import com.sun.amr.vo.Details;
import com.sun.amr.vo.Emp;
@Controller
@RequestMapping("/pages/res/*")
public class DetailsAction extends AbstractActionAdapter{
	@Resource
	private IDetailsService detailsService;
	
	/**
	 * 增加前的处理方法，实现数据回显
	 * @param request
	 * @return
	 */
	@RequestMapping("addPre")
	public ModelAndView addPre(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		try {
			//查询一级类目
			Map<String, Object> map=this.detailsService.addPre(super.getEid(request));
			mav.addObject("allTypes",map.get("allTypes"));
			//指定跳转页面（尽心信息编辑的页面）
			mav.setViewName(super.getMsg("details.add.page"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 添加数据
	 * @param vo   信息对象
	 * @param pic  图片
	 * @param request
	 * @return
	 */
	@RequestMapping("add")
	public ModelAndView add(Details vo,MultipartFile pic,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (pic!=null&&pic.getSize()>0) {
			vo.setPhoto(super.createSingleFileName(pic));//生成图片名称
		}
		if (super.isAuth(25, request)) {
			try {
				if (this.detailsService.add(vo, super.getEid(request))) {
					super.saveUploadFile(pic, vo.getPhoto(), request);
					//保存提示信息，在forward.jsp显示
					super.setMsgAndUrl("vo.add.success", "details.list.action", mav);
				}else {
					//增加成功后直接跳转到待购清单列表显示的访问路径，进行列表显示
					super.setMsgAndUrl("vo.add.failure", "details.list.action", mav);
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.add.failure", "details.list.action", mav);
			}
			mav.setViewName(super.getMsg("forward.page"));;
		}else {
			mav.setViewName(super.getMsg("errors.page"));;
		}
		return mav;
	}
	/**
	 * 获取待购清单列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("listDetails")
	public ModelAndView listDetails(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(25, request)) {
			try {
				
				//将查询的列表数据保存
				mav.addObject("allDetails",this.detailsService.listDetails(super.getEid(request)));
				List<Details> list=this.detailsService.listDetails(super.getEid(request));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//跳转到待购清单列表
			mav.setViewName(super.getMsg("details.list.page"));
		}else {
			//跳转到错误页面
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	/**
	 * 实现清单的批量删除
	 * @param deletestr  要删除的清单编号，这里取得是一个字符串，是“|”链接的字符串
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("rm")
	public ModelAndView rm(String deletestr,HttpServletResponse response,HttpServletRequest 
			request) {
		if (super.isAuth(25, request)) {//25号权限
			//保存删除清单编号的集合
			List<Integer> deleteList=new ArrayList<Integer>();
			if (!(deletestr==null||"".equals(deletestr))) {
				//将字符串按照“|”拆分
				String deleteResult[]=deletestr.split("\\|");
				for(int x=0;x<deleteResult.length;x++) {
					//将每个拆分的编号保存到List集合中
					deleteList.add(Integer.parseInt(deleteResult[x]));
				}
				try {
					Map<String, Object> map=this.detailsService.rm(super.getEid(request), deleteList);
					//取得的所有清单信息进行迭代
					List<Details> allDetails=(List<Details>) map.get("allDetails");
					if (allDetails!=null) {
						Iterator<Details> iter=allDetails.iterator();
						while(iter.hasNext()) {//逐步删除对应的照片
							super.deleteFile(iter.next().getPhoto(), request);
						}
					}
					//将最终结果返回给客户端
					super.print(response, map.get("flag"));
				} catch (Exception e) {
					e.printStackTrace();
					super.print(response, false);
				}
			}else {
				super.print(response, false);
			}
		}
		return null;
	}
	/**
	 * 实现待购清单的数量修改
	 * @param updatestr  要修改的清单的信息（以“编号：数量|编号：数量”）
	 * @param deletestr  要删除的清单的编号信息（以“编号|编号|编号”），这些编号的清单的数量都是0
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("editAmount")
	public ModelAndView editAmount(String updatestr,String deletestr,HttpServletResponse response,
			HttpServletRequest request) {
		if (super.isAuth(25, request)) {//验证权限
			//将数量不为0的字符串拆分
			String updateResult[]=updatestr.split("\\|");
			//updateMap保存的是需要修改的清单的编号和数量：key=编号，value=数量
			Map<Integer, Integer> updateMap=new HashMap<Integer,Integer>();
			for(int x=0;x<updateResult.length;x++) {
				//将每一个键对数据再进行拆分
				String temp[]=updateResult[x].split(":");
				updateMap.put(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
			}
			//保存要删除的清单的编号
			List<Integer> deleteList=new ArrayList<Integer>();
			if (!(deletestr==null||"".equals(deletestr))) {
				String deleteResult[]=deletestr.split("\\|"); //按照“|”拆分
				for(int x=0;x<deleteResult.length;x++) {  //将编号保存到List集合中
					deleteList.add(Integer.parseInt(deleteResult[x]));
				}
			}
			//数据准备完毕后调用业务层的方法实现处理
			try {
				Map<String, Object> map=this.detailsService.editAmount(super.getEid(request), updateMap, 
						deleteList);
				//返回map集合，集合中保存了删除清单的信息和最终处理结果
				List<Details> allDetails=(List<Details>)map.get("allDetails");
				if (allDetails!=null) {
					Iterator<Details> iter=allDetails.iterator();
					while(iter.hasNext()) {
						//删除对应图片
						super.deleteFile(iter.next().getPhoto(), request);
					}
				}
				//输出最终处理标记
				super.print(response, map.get("flag"));
			} catch (Exception e) {
				e.printStackTrace();
				super.print(response, false);
			}
		}else {
			super.print(response, false);
		}
		return null;
	}
	/**
	 * 编辑前的准备操作
	 * @param did   清单编号
	 * @param request
	 * @return
	 */
	@RequestMapping("editPre")
	public ModelAndView editPre(int did,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		try {
			Map<String , Object> map=this.detailsService.editPre(super.getEid(request), did);
			mav.addObject("allTypes",map.get("allTypes"));
			mav.addObject("allSubtypes",map.get("allSubtypes"));
			mav.addObject("details",map.get("details"));
			//跳转到编辑页面（res_edit.jsp）
			mav.setViewName(super.getMsg("details.edit.page"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 实现清单的编辑
	 * @param vo  编辑的清单详情对象
	 * @param pic   新上传图片
	 * @param request
	 * @return
	 */
	@RequestMapping("edit")
	public ModelAndView edit(Details vo,MultipartFile pic,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		if (super.isAuth(25, request)) {//权限验证
			vo.setEmp(new Emp());  //设置操纵人的编号
			vo.getEmp().setEid(super.getEid(request));  //设置操作人的编号
			try {
				if (this.detailsService.edit(super.getEid(request), vo)) {
					//如果从客户端重新上传了照片则将之前的照片覆盖
					if (pic!=null&&pic.getSize()>0) {
						super.saveUploadFile(pic, vo.getPhoto(), request);
					}
					//在forward.jsp中给出的提示信息,跳转到/pages/res/listDetails.action
					super.setMsgAndUrl("vo.edit.success", "details.list.action", mav);
				}else {
					super.setMsgAndUrl("vo.edit.failure", "details.list.action", mav);
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.edit.failure", "details.list.action", mav);
			}
			mav.setViewName(super.getMsg("forward.page"));
		}else {
			mav.setViewName(super.getMsg("errors.page"));
		}
		return mav;
	}
	/**
	 * 实现用品的追加处理，是异步请求的调用
	 * @param rid   用品的编号
	 * @param request
	 * @param response
	 */
	@RequestMapping("append")
	public void append(int rid,HttpServletRequest request,HttpServletResponse response) {
		if (super.isAuth(25, request)) {
			try {
				super.print(response, this.detailsService.addAppend(super.getEid(request), rid));
			} catch (Exception e) {
				e.printStackTrace();
				super.print(response, false);
			}
		}else {
			super.print(response, false);
		}
	}
	
	@Override
	public String getSaveFileDiv() {
		return "/upload/res/";
	}
}

















