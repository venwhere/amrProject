package com.sun.amr.service;
import java.util.List;
import com.sun.amr.vo.Type;
public interface ITypeService {
	/**
	 * 实现类别的全部查询
	 * @return  类别的集合
	 * @throws Exception
	 */
	public List<Type> list() throws Exception;
}
