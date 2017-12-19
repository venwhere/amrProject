package com.sun.amr.dao;
import java.util.List;
import java.util.Map;

import com.sun.amr.vo.Emp;
public interface IEmpDAO extends IDAO<Integer, Emp>{
	/**
	 * 登录之后要求取出的真实姓名以及用户的照片
	 * @param vo  要验证登陆的用户
	 * @return   返回执行结果的布尔值
	 * @throws Exception
	 */
	public Emp findLogin(Emp vo) throws Exception;
	/**
	 * 实现模糊分页查询
	 * @param paramMap  保存了查询条件的map集合
	 * @return   放回查询到雇员集合
	 * @throws Exception
	 */
	public List<Emp> findAllAdmin(Map<String, Object> paramMap) throws Exception;  
	/**
	 * 统计查询到的数据总数
	 * @param paramMap  保存了查询条件的map集合
	 * @return  查询到的数据总数
	 * @throws Exception
	 */
	public Integer getAllAdminCount(Map<String, Object> paramMap) throws Exception;
	/**
	 * 人事部门页面分页查询员工的信息
	 * @param paramMap   保存了查询条件的Map集合
	 * @return   返回员工信息
	 * @throws Exception
	 */
	public List<Emp> findAllEmp(Map<String, Object> paramMap) throws Exception;
	/**
	 * 人事部门页面统计查询到的员工记录总数
	 * @param paramMap   保存了查询条件的Map集合
	 * @return   返回记录总数
	 * @throws Exception
	 */
	public Integer getAllEmpCount(Map<String, Object> paramMap) throws Exception;
}





























