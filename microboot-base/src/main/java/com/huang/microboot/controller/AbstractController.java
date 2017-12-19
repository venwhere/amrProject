package com.huang.microboot.controller;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.annotation.Resource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
public class AbstractController {
	@Resource
	private MessageSource messageSource;
	public String getMsg(String key, String... args) {
		return this.messageSource.getMessage(key, args, Locale.getDefault());
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 创建一个日期格式化类
		System.out.println("------------------------------------------");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, false));
	}
}
