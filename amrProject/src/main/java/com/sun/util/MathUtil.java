package com.sun.util;

public class MathUtil {
	/**
	 * ʵ���������봦�����
	 * @param num Ҫ����������
	 * @param scale �������ݵı���С��λ
	 * @return
	 */
	public static double round(double num, int scale) {
		return Math.round(num * Math.pow(10, scale)) / Math.pow(10, scale) ;
	} 
}
