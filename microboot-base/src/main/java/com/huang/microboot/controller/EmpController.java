package com.huang.microboot.controller;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.huang.microboot.service.IDeptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
@Controller
public class EmpController extends AbstractController {
	@Resource
	private IDeptService deptService;
	@RequestMapping(value="/addPre",method=RequestMethod.GET)
	public String add(HttpServletRequest req, HttpServletResponse resp, Model model) {
		return "pages/emp";
	}
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object upload(String name,HttpServletRequest req) throws IOException {
		if(req instanceof MultipartHttpServletRequest) { 
			MultipartHttpServletRequest mrequest=(MultipartHttpServletRequest)req;
			List<MultipartFile> photos=mrequest.getFiles("photo");
			Iterator<MultipartFile> iter=photos.iterator();
			while(iter.hasNext()) {
				MultipartFile photo=iter.next();
				if(photo!=null&&photo.getSize()>0) {
					System.out.println("文件原始名称:"+photo.getOriginalFilename());
					System.out.println("文件大小:"+photo.getSize());
					System.out.println("文件的类型:"+photo.getContentType());
					System.out.println("文件输入流对象:"+photo.getInputStream());
				}
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		}
		return "文件上传成功!!!!";
	}
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list() throws Exception {
		List<String> depts=deptService.list();
		return depts;
	}
}













