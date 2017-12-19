package com.sun.amr.service;

import java.util.Map;

import com.sun.amr.vo.Emp;

public interface IEmpService {
	/**
	 * 增加雇员之前的基本信息回显查询，需要调用数据层的ILevelDAO.findAll()方法和IDeptDAO.findAllBySflag()
	 * 增加雇员是需要为其选择部门，所以要将部门查询出来
	 * @return   返回数据如下：
	 * key=allLevels,value=ILevelDAO.findAll()
	 * key=allDepts,value=IDeptDAO.findAllBySflag()
	 * @throws Exception
	 */
	public Map<String, Object> addPre() throws Exception;
	/**
	 * 在增加雇员的时候需要远程验证编号是否已经存在
	 * @param eid   输入的编号
	 * @return
	 * @throws Exception
	 */
	public boolean checkEid(int eid) throws Exception;
	/**
	 * 实现雇员的增加操作，调用的是EmpDAO.doCreate()方法
	 * @param vo   包含了要增加的信息的vo对对象
	 * @param eid   操作人的编号
	 * @return
	 * @throws Exception
	 */
	public boolean add(Emp vo,Integer eid) throws Exception;
	/**
	 * 实现模糊分页查询
	 * @param eid  操作人的编号
	 * @param column  模糊查询的字段
	 * @param keyWord 模糊查询的关键字
	 * @param currentPage  当前页
	 * @param lineSize  每页显示的页数
	 * @return  key=allEmps,value=IEmpDAO.findAllEmp()
	 *          key=count,value=IEmpDAO.findAllEmpCount()
	 * @throws Exception
	 */
	public Map<String,Object> list(int eid,String column,String keyWord,
			int currentPage,int lineSize) throws Exception;
	/**
	 * 实现数据的回显查询
	 * @param eid   需要被改动的雇员的编号
	 * @return  key=allLevels,value=ILevelDAO.findAll()
	 * 			key=allDepts,value=IDeptDAO.findAllBySflag()
	 * 			key=emp,value=EmpDAO.findById()
	 * @throws Exception
	 */
	public Map<String, Object> eidtPre(Integer eid) throws Exception;
	/**
	 * 实现雇员信息的编辑操作
	 * @param vo   包含了编辑信息的vo对象
	 * @param eid   操作人员的编号
	 * @return
	 * @throws Exception
	 */
	public boolean edit(Emp vo,Integer eid) throws Exception;
}





















