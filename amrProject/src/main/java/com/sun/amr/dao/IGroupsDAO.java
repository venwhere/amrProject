package com.sun.amr.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.sun.amr.vo.Groups;
@Mapper
public interface IGroupsDAO extends IDAO<Integer, Groups>{
	/**
	 * 根基部门的编号查询权限组信息
	 * @param did   部门编号
	 * @return    返回权限组
	 * @throws Exception
	 */
	public List<Groups> findAllByDept(Integer did) throws Exception;
}
