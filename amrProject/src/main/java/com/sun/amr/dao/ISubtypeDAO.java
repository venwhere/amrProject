package com.sun.amr.dao;
import java.util.List;
import com.sun.amr.vo.Subtype;
public interface ISubtypeDAO extends IDAO<Integer, Subtype>{
	/**
	 * 实现根据父类编号查询对应的子类别信息
	 * @param tid  父类编号
	 * @return
	 * @throws Exception
	 */
	public List<Subtype> findAllByType(Integer tid) throws Exception;
}
