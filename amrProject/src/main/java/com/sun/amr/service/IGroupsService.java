package com.sun.amr.service;
import java.util.List;
import com.sun.amr.vo.Groups;
public interface IGroupsService {
	/**
	 * 根据部门的编号查询对应的权限组信息，调用的是数据层的findAllByDept()方法
	 * @param eid   操作人员的编号，权限的验证
	 * @param did   部门的编号
	 * @return
	 * @throws Exception
	 */
	public List<Groups> getAllByDept(Integer eid,Integer did) throws Exception;
}
