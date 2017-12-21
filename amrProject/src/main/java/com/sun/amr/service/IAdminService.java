package com.sun.amr.service;
import java.util.Map;
import com.sun.amr.vo.Emp;
public interface IAdminService {
	/**
	 * 实现登录操作，调用的IEmpDAO接口中的findLogin()方法
	 * @param vo  保存了编号和密码的vo类
	 * @return   返回执行结果的布尔值
	 * @throws Exception
	 */
	public Emp login(Emp vo) throws Exception;
	/**
	 * 增加管理员前的数据准备查询
	 * @return   保存了雇员的级别信息的数据
	 * @throws Exception
	 */
	public Map<String, Object> addPre() throws Exception;
	/**
	 * 实现用户编号的远程验证，调用的是数据层finById()的方法
	 * @param eid   输入雇员编号
	 * @return  返回验证结果
	 * @throws Exception
	 */
	public boolean checkEid(int eid) throws Exception;
	/**
	 * 实现雇员(管理员)的增加，调用的是数据层的doCreate()方法
	 * @param vo 包含要添加的数据的vo对象
	 * @param eid  操作人的编号
	 * @return
	 * @throws Exception
	 */
	public boolean add(Emp vo,Integer eid) throws Exception;
	/**
	 * 实现管理员的模糊分页查询
	 * @param eid   该管理员的编号，用于权限验证
	 * @param column   查询的字段
	 * @param keyWord   关键字
	 * @param currentPage  当前页
	 * @param lineSize   每页显示的数据量
	 * @return   返回每页的数据
	 * @throws Exception
	 */
	public Map<String, Object> list(int eid,String column,String
			keyWord,int currentPage,int lineSize) throws Exception;
}























