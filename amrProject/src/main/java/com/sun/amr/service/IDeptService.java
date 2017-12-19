package com.sun.amr.service;
import java.util.List;
import com.sun.amr.vo.Dept;
public interface IDeptService {
	/**
	 * 查询所有部门的信息，调用的是dao层的findAll()方法
	 * @param eid   当前雇员的编号,用作权限的验证
	 * @return    返回查找的部门信息
	 * @throws Exception
	 */
	public List<Dept> list(Integer eid) throws Exception;
	/**
	 * 实现部门信息的修改
	 * @param eid   操作人的编号，实现权限验证
	 * @param vo  要修改的数据
	 * @return
	 * @throws Exception
	 */
	public boolean edit(Integer eid,Dept vo) throws Exception;
}















