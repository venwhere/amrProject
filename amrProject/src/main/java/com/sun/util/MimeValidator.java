package com.sun.util;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
public class MimeValidator {
	public static boolean isMime(Object obj, HttpServletRequest request) { // 是否指定的mime数据
		// 1、必须确定好是否有上传文件
		MultipartResolver mr = new CommonsMultipartResolver(); // 准备进行上传文件的操作处理
		if (mr.isMultipart(request)) {	// 当前存在有上传文件
			// 2、取得定义的mime验证规则
			String mimeContent = MessageUtil.getMessage(obj, "mimeType");
			if (mimeContent == null || "".equals(mimeContent)) { // 表示现在没有规则
				return true;
			} else {	// 需要针对于每一个操作进行规则的匹配检测
				String mimeRules [] = mimeContent.split("\\|") ;// 取出全部的类型
				MultipartRequest mreq = (MultipartRequest) request ;	// 处理上传的request
				Map<String, MultipartFile> map = mreq.getFileMap(); // 取得全部的上传内容
				if (map.size() > 0) {	// 当前的表单里面已经定义有指定的上传处理操作
					Iterator<Map.Entry<String, MultipartFile>> iter = map.entrySet().iterator() ;
					while (iter.hasNext()) {
						Map.Entry<String, MultipartFile> me = iter.next() ;
						// System.out.println("参数名称：" + me.getKey() + "，文件大小：" + me.getValue().getSize());
						if (me.getValue().getSize() > 0 ) {	// 现在这个参数的表单有文件上传，那么就需要进行类型的匹配
//							System.out.println("** mime = " + me.getValue().getContentType());
//							System.out.println("** rules = " + Arrays.toString(mimeRules));
//							System.out.println("** 判断 = " + ValidatorUtil.isMime(mimeRules, me.getValue().getContentType()));
							if (!ValidatorRules.isMime(mimeRules, me.getValue().getContentType())) {
								return false ;
							}
						}
					}
				}
			} 
		}
		return true ;
	}
}
