package com.sun.amr.dao;
import com.sun.amr.vo.Res;
public interface IResDAO extends IDAO<Integer,Res>{
	/**
	 * 实现数量的修改
	 * @param rid  用品的编号
	 * @param amount   增加的数量
	 * @return
	 * @throws Exception
	 */
	public int doUpdateAmount(Integer rid,Integer amount) throws Exception;
}















