package com.sun.amr.dao;
import java.util.List;
import java.util.Map;

import com.sun.amr.vo.Action;
public interface IActionDAO extends IDAO<Integer, Action>{
	/**
	 * 根据权限组编号查询权限信息
	 * @param gid   权限组编号
	 * @return   返回权限的集合
	 * @throws Exception
	 */
	public List<Action> findAllByGroups(Integer gid) throws Exception;
	/**
	 * 根据部门与权限查询指定的权限数据
	 * @param did   部门的编号
	 * @param actid  权限的编号
	 * @return
	 * @throws Exception
	 */
	public List<Action> findByIdAndDept(Map<String, Object> map) throws Exception;
}
