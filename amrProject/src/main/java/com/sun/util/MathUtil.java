package com.sun.util;

public class MathUtil {
	/**
	 * 实现四舍五入处理操作
	 * @param num 要操作的数据
	 * @param scale 设置数据的保留小数位
	 * @return
	 */
	public static double round(double num, int scale) {
		return Math.round(num * Math.pow(10, scale)) / Math.pow(10, scale) ;
	} 
}
