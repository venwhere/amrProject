package com.sun.util;
import java.lang.reflect.Method;
public class MessageUtil {
	public static String getMessage(Object obj, String key) {
		try {
			Method msgSourceMethod = obj.getClass().getMethod(
					"getMsg", String.class);
			return (String) msgSourceMethod.invoke(obj, key);
		} catch (Exception e) {
			e.printStackTrace();  
			return null ;
		}
	}
}
