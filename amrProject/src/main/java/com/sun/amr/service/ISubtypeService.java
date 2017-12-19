package com.sun.amr.service;
import com.sun.amr.vo.Subtype;
import java.util.List;
public interface ISubtypeService {
	/**
	 * 商品的分类列表
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public List<Subtype> list(int tid) throws Exception;
}
