package com.sun.amr.service;

import java.util.Map;

public interface IResService {
	/**
	 * 实现模糊分页查询，调用数据层的findAllSplit()和findAllCount()方法
	 * @param column  模糊查询的字段
	 * @param keyWord  模糊查询的关键字
	 * @param currentPage   当前页
	 * @param lineSize   每页显示的数据量
	 * @return  返回一个map集合：<br>
	 * <li>用品的信息：key=allReses,value=IResDAO.findAllSplit()</li>
	 * <li>分页的信息：key=pager,value=new Page()</li>
	 * @throws Exception
	 */
	public Map<String, Object> list(String column,String keyWord,
			int currentPage,int lineSize) throws Exception;
}









