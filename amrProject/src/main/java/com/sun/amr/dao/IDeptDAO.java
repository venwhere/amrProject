package com.sun.amr.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.sun.amr.vo.Dept;
@Mapper
public interface IDeptDAO extends IDAO<Integer, Dept>{
	/**
	 * 根据部门的特殊标记进行查询
	 * @param sflag   特殊标记，这里设置为0，表示普通部门
	 * @return
	 * @throws Exception
	 */
	public List<Dept> findAllBySflag(Integer sflag) throws Exception;
}
