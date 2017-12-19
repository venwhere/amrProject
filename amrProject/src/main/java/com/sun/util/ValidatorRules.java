package com.sun.util;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
public class ValidatorRules {
	private Object obj ;  				//��ʾ������������Action��
	private String rules[];		 		//��֤����
	private HttpServletRequest request; //����ȡ���û����ݵĲ���
	public  ValidatorRules() {};
	/**
	 * ���췽�������Ҫʹ�øù�������Ҫ�ȴ�������
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
			String temp [] = this.rules[x].split(":") ;	// �������߲��
			String paramValue = this.request.getParameter(temp[0]) ;
			switch(temp[1]) {
				case "rand" : {
					if (isString(paramValue)) {	// �������ڲ�Ϊ��
						String rand = (String) this.request.getSession().getAttribute("rand") ;
						if (!paramValue.equalsIgnoreCase(rand)) {	// ��֤����֤ʧ��
							errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.code.error.msg")) ;
						}
					} else {
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.code.error.msg")) ;
					}
				}
				case "string" : {
					if (!isString(paramValue)) {	// ��֤ʧ��
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.string.error.msg")) ;
					}
					break ;
				}
				case "int" : {
					if (!isInt(paramValue)) {	// ��֤ʧ��
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.int.error.msg")) ;
					}
					break ;
				}
				case "double" : {
					if (!isDouble(paramValue)) {	// ��֤ʧ��
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.double.error.msg")) ;
					}
					break ;
				}
				case "date" : {
					if (!isDate(paramValue)) {	// ��֤ʧ��
						errors.put(temp[0], MessageUtil.getMessage(this.obj, "invalidate.date.error.msg")) ;
					}
					break ;
				}
				case "datetime" : {
					if (!isDate(paramValue)) {	// ��֤ʧ��
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
		if (isString(str)) {	// ���ݲ�Ϊ��
			return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") ;
		}
		return false ;
	} 
	public static boolean isMime(String mimeRules [] ,String mime) {
		if (isString(mime)) {	// ������ݲ�Ϊ��
			for (int x = 0 ; x < mimeRules.length ; x ++) {
				if (mime.equalsIgnoreCase(mimeRules[x])) {
					return true ;
				}
			}
		}
		return false ; 
	}
}
