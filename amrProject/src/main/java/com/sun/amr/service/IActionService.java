package com.sun.amr.service;
import java.util.List;
import com.sun.amr.vo.Action;
public interface IActionService {
	/**
	 * 根据权限组的编号查询对应的权限信息，调用的是IActionDAO中的fingAllByGroups()方法
	 * @param eid   操作人的编号
	 * @param gid   权限组的编号s
	 * @return
	 * @throws Exception
	 */
	public List<Action> getAllByGroups(Integer eid,Integer gid) throws Exception;
}














