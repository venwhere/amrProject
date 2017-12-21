package com.sun.util;
import java.util.Base64;
public class PasswordUtil {
	//private static String text="www.huang.org";
	//private static String salt=new String(Base64.getEncoder().encode(text.getBytes()));
	public static String getPassword(String mid,String password) {
		String salt=new String(Base64.getEncoder().encode(mid.getBytes()));
		return new MD5Code().getMD5ofStr(password+"{{"+salt+"}}");
	}
	
	/*public static void main(String[] args) {
		String text="www.huang.org";
		String salt=new String(Base64.getEncoder().encode(text.getBytes()));
		System.out.println(salt);
		String MD5password=new MD5Code().getMD5ofStr("java"+"{{"+salt+"}}");
		System.out.println(MD5password);
	}*/
	public static void main(String[] args) {
		System.err.println(getPassword("2001","hello"));
	}
}
