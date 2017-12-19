package com.sun.amr.dao;
import java.util.List;
import java.util.Map;

import com.sun.amr.vo.Purchase;
public interface IPurchaseDAO extends IDAO<Integer, Purchase>{
	/**
	 * 查询当前的用户所用申请清单信息
	 * @param paramMap  保存了查询条件的map集合
	 * @return  所有清单的集合
	 * @throws Exception
	 */
	public List<Purchase> findAllByEmp(Map<String, Object> paramMap) throws Exception;
	/**
	 * 所有申请记录的个数统计
	 * @param eid  当前用户的编号
	 * @return  数据总数
	 * @throws Exception
	 */
	public Integer getAllCountByEmp(Integer eid) throws Exception;
	/**
	 * 根据申请单的编号查询详情信息
	 * @param pid  申请单的编号
	 * @param eid  雇员的编号
	 * @return
	 * @throws Exception
	 */
	public Purchase findByIdAndEmp(Integer pid,Integer eid) throws Exception;
	/**
	 * 为财务部门分页查询所有的申请单信息
	 * @param start  开始位置
	 * @param lineSize   每页显示的数据量
	 * @return   所有申请单集合
	 * @throws Exception
	 */
	public List<Purchase> findAllSimpleSplit(Integer start,Integer lineSize) throws Exception;
	/**
	 * 为财务部门统计查询到的数据量
	 * @return  数据总数
	 * @throws Exception
	 */
	public Integer getAllCountSimple() throws Exception;
	/**
	 * 实现申请单的审核操作
	 * @param pid  申请单的编号
	 * @param status  审核状态
	 * @param eid  审核人的编号
	 * @return
	 * @throws Exception
	 */
	public int doUpdateStatus(Integer pid,Integer status,Integer eid) throws Exception;
}




















