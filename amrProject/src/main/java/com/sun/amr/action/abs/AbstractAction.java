package com.sun.amr.action.abs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sun.amr.vo.Action;
import com.sun.amr.vo.Emp;
import com.sun.amr.vo.Groups;
import com.sun.util.SplitUtil;

public abstract class AbstractAction {
	@Resource
	private MessageSource msgSource;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	/**
	 * 设置所需要的msg与url数据
	 * 
	 * @param msgKey
	 * @param urlKey
	 * @param mav
	 */
	public void setMsgAndUrl(String msgKey, String urlKey, ModelAndView mav) {
		mav.addObject("msg", this.getMsg(msgKey));
		mav.addObject("url", this.getMsg(urlKey));
	}
	/**
	 * 设置操作资源文件数据的读取处理
	 * 
	 * @param key
	 *            资源文件的key信息
	 * @return
	 */
	 public String getMsg(String key) {
	 return this.msgSource.getMessage(key, new Object[]
	 {this.getFlag()},Locale.getDefault());
	 }
	/**
	 * 传递任意多个参数
	 * 
	 * @param key
	 * @param arg
	 * @return
	 */
	public String getMsg(String key, Object... arg) {
		return this.msgSource.getMessage(key, arg, Locale.getDefault());
	}
	/**
	 * 取得文件名称，如果没有上传，则返回的是"nophoto.png"文件
	 * 
	 * @param photo
	 *            接收上传文件
	 * @return
	 */
	public String createSingleFileName(MultipartFile file) {
		if (file == null) {
			return "nophoto.png";
		}
		if (file.getSize() <= 0) {// 没有上传文件
			return "nophoto.png";
		}
		return UUID.randomUUID() + "." + this.getFileExt(file.getContentType());
	}
	/**
	 * 实现文件的保存
	 * 
	 * @param photo
	 * @param newFileName
	 * @param req
	 * @return
	 */
	public boolean saveUploadFile(MultipartFile file, String fileName, HttpServletRequest req) {
		String path = req.getServletContext().getRealPath(this.getSaveFileDiv()) + fileName;
		File outFile=new File(path);
		if (file.getSize() > 0) {
			OutputStream output = null;
			InputStream input = null;
			try {
				if(!outFile.getParentFile().exists()) {
					outFile.getParentFile().mkdirs();
				}
				output = new FileOutputStream(path);
				input = file.getInputStream();
				byte data[] = new byte[2048];
				int temp = 0;
				while ((temp = input.read(data)) != -1) {
					output.write(data, 0, temp);
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(input!=null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}
	/**
	 * 返回文件要保存的路径字符串
	 * 
	 * @return
	 */
	public abstract String getSaveFileDiv();

	/**
	 * 删除文件信息
	 * 
	 * @param fileName
	 * @param req
	 * @return
	 */
	public boolean deleteFile(String fileName, HttpServletRequest req) {
		if (!"nophoto.png".equals(fileName)) {// 如果是系统默认的图片则不让删除
			String path = req.getServletContext().getRealPath(getSaveFileDiv()) + fileName;
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			} else {
				return false;
			}
		}

		return true;
	}

	/**
	 * 取得文件后缀
	 * 
	 * @param contentType
	 * @return
	 */
	public String getFileExt(String contentType) {
		if ("image/bmp".equals(contentType)) {
			return "bmp";
		} else if ("image/gif".equals(contentType)) {
			return "gif";
		} else if ("image/jpeg".equals(contentType)) {
			return "jpeg";
		} else if ("image/png".equals(contentType)) {
			return "png";
		}
		return null;
	}
	/**
	 * 取出当前用户的所有信息
	 * @param request
	 * @return
	 */
	public Emp getEmp(HttpServletRequest request) {
		return (Emp)request.getSession().getAttribute("emp");
	}
	/**
	 * 取得当前登陆的用户的编号
	 * @param request
	 * @return
	 */
	public Integer getEid(HttpServletRequest request) {
		return ((Emp)request.getSession().getAttribute("emp")).getEid();
	}
	/**
	 * 判断当前的用户是否有该权限
	 * @param actid
	 * @param request
	 * @return
	 */
	public boolean isAuth(int actid,HttpServletRequest request) {
		Emp emp=this.getEmp(request);
		//迭代当前用户的权限组信息
		Iterator<Groups> iterGroups=emp.getDept().getAllGroups().iterator();
		while(iterGroups.hasNext()) {
			Groups groups=iterGroups.next();
			//迭代每个权限组的权限信息
			Iterator<Action> iterAction=groups.getAllActions().iterator();
			while(iterAction.hasNext()) {
				Action act=iterAction.next();
				//如果指定的权限编号在当前用户的权限内则返回true
				if(act.getActid().equals(actid)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 实现信息的输出操作
	 * @param response
	 * @param obj
	 */
	public void print(HttpServletResponse response,Object obj) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 处理客户端传递的参数
	 * @param request  主要是取得客户端传递的参数
	 * @param split  这是工具类的对象，处理客户端传递的参数
	 */
	public void handleSplit(HttpServletRequest request,SplitUtil split) {
		//将取得的参数保存到split对象中
		split.setCp(request.getParameter("cp"));
		split.setLs(request.getParameter("ls"));
		split.setKw(request.getParameter("kw"));
		split.setCol(request.getParameter("col"));
		//将需要在页面获取的一些参数保存到request属性范围
		request.setAttribute("column", split.getColumn());
		request.setAttribute("keyWord", split.getKeyword());
		//在页面中生成下拉列表的数据，具体的数据在子类中定义
		request.setAttribute("columnData", this.getColumnData());
	}
	/**
	 *将查询到的数据从map集合中取出后保存到ModelAndView中
	 * @param map  保存了数据的map集合
	 * @param mav  ModelAndView对象
	 */
	public void setPageMsg(Map<String, Object> map,ModelAndView mav) {
		mav.addObject("allEmps",map.get("allEmps"));
		mav.addObject("pager",map.get("pager"));
		mav.addObject("url",map.get("url"));
	} 
	/**
	 * 得到分页的默认列，具体的内容是子类指定的
	 * @return
	 */
	public abstract String getDefaultColumn();
	/**
	 * 实现页面的迷糊查询列表显示，格式“标签：字段|标签：字段|标签：字段|”
	 * @return
	 */
	public abstract String getColumnData();
	/**
	 * 返回CRUD操作时的执行标记
	 * @return
	 */
	public abstract String getFlag();
}



























