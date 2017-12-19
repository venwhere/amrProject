package com.sun.util;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
public class ValidatorRules {
	private Object obj ;  				//表示触发拦截器的Action类
	private String rules[];		 		//验证规则
	private HttpServletRequest request; //可以取得用户传递的参数
	public  ValidatorRules() {};
	/**
	 * 构造方法，如果要使用该工具类需要先创建对象
	 * @param obj
	 * @param rules
	 * @param request
	 */
	public ValidatorRules(Object obj , String rules[], HttpServletRequest request) {
		this.obj = obj ;
		this.rules = rules;
		this.request = request;
	}
	public Map<String,String> validate() {
		Map<String,String> errors = new HashMap<String,String>() ;
		for (int x = 0 ; x < this.rules.length ; x ++) {
			String temp [] = this.rules[x].split(":") ;	// 按照竖线拆分
			String paramValue = this.request.getParameter(temp[0]) ;
			switch(temp[1]) {
				case "rand" : {
					if (isString(paramValue)) {	// 数据现在不为空
						String rand = (String) this.request.getSession().getAttribute("rand") ;
						if (!paramValue.equalsIgnoreCase(rand)) {	// 验证码验证失败
							errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.code.error.msg")) ;
						}
					} else {
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.code.error.msg")) ;
					}
				}
				case "string" : {
					if (!isString(paramValue)) {	// 验证失败
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.string.error.msg")) ;
					}
					break ;
				}
				case "int" : {
					if (!isInt(paramValue)) {	// 验证失败
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.int.error.msg")) ;
					}
					break ;
				}
				case "double" : {
					if (!isDouble(paramValue)) {	// 验证失败
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.double.error.msg")) ;
					}
					break ;
				}
				case "date" : {
					if (!isDate(paramValue)) {	// 验证失败
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.date.error.msg")) ;
					}
					break ;
				}
				case "datetime" : {
					if (!isDate(paramValue)) {	// 验证失败
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.datetime.error.msg")) ;
					}
					break ;
				}
			}
		}
		return errors ;
	} 
	public static boolean isString(String str) {
		if (str == null || "".equals(str)) {	 
			return false ;
		}
		return true ;
	}
	public static boolean isInt(String str) {
		if (isString(str)) {	 
			return str.matches("\\d+") ;
		}
		return false ;
	}
	public static boolean isDouble(String str) {
		if (isString(str)) {	 
			return str.matches("\\d+(\\.\\d+)?") ;
		}
		return false ;
	}
	public static boolean isDate(String str) {
		if (isString(str)) {	 
			return str.matches("\\d{4}-\\d{2}-\\d{2}") ;
		}
		return false ;
	}
	public static boolean isDatetime(String str) {
		if (isString(str)) {	// 数据不为空
			return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") ;
		}
		return false ;
	} 
	public static boolean isMime(String mimeRules [] ,String mime) {
		if (isString(mime)) {	// 如果数据不为空
			for (int x = 0 ; x < mimeRules.length ; x ++) {
				if (mime.equalsIgnoreCase(mimeRules[x])) {
					return true ;
				}
			}
		}
		return false ; 
	}
}
