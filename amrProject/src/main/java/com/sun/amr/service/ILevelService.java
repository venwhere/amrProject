package com.sun.amr.service;

public interface ILevelService {
	/**
	 * 检查输入的工资是否在指定的雇员等级之中
	 * @param salary   输入的工资数据
	 * @param lid   雇员的等级信息
	 * @return   如果满足等级验证则返回true,否则返回false
	 * @throws Exception
	 */
	public boolean checkSalary(double salary,int lid) throws Exception;
}
