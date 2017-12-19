package com.sun.amr.service;

import java.util.Map;

import com.sun.amr.vo.Purchase;

public interface IPurchaseService {
	/**
	 * 实现清单的申请提交，调用的方法：<br>
	 * <li>IPurchaseDAO.doCreate()</li>
	 * <li>IDetailsDAO.doUpdateByPurchase()</li>
	 * @param eid   操作人的编号
	 * @param vo  保存了申请信息的vo对象
	 * @return  
	 * @throws Excepiton
	 */
	public boolean add(int eid,Purchase vo) throws Exception;
	/**
	 * 实现申请单的查询，调用的数据层的方法：findAllByEmp()和findAllCountByEmp()
	 * @param eid  当前用户的编号
	 * @param currentPage  当前页
	 * @param lineSize   每页显示的数量
	 * @return   返回数据<br>
	 * <li> 申请单的信息：key=allPurchases,value=IPurchaseDAO.findAllByEmp</li>
	 * <li>分页的信息：key=pager,value=new Page(currentPage,allPages)</li>
	 * @throws Exception
	 */
	public Map<String, Object> listByEmp(int eid,int currentPage,int lineSize) throws Exception;
	/**
	 * 实现根据申请单编号和用户编号查询详情信息，调用的是数据层的findByIdAndEmp()方法
	 * @param eid  操作人的编号
	 * @param pid    申请单的编号
	 * @return   如果有数据返回申请单的信息信息，否则返回null
	 * @throws Exception
	 */
	public Purchase getByEmp(int eid,int pid) throws Exception;
	/**
	 * 为财务部门查询清单信息，调用数据层的findAllSimpleSplit()和getAllCountSplit()
	 * @param eid   当前用户的编号（用作权限验证）
	 * @param currentPage  当前页
	 * @param lineSize   每页显示的数据量
	 * @return  返回的数据：<br>
	 * <li>申请单信息：key=allPurchase,value=IPurchaseDAO.findAllSimpleSplit</li>
	 * <li>分页信息：key=pager,value=new Page()</li>
	 * @throws Exception
	 */
	public Map<String, Object> listSimple(int eid,int currentPage,int lineSize) throws Exception;
	/**
	 * 根据申请单的编号进行详细信息的查询，调用数据层的findById()fangf
	 * @param eid   当前用户的编号
	 * @param pid   申请单的编号
	 * @param actid 权限编号
	 * @return  申请单的集合
	 * @throws Exception
	 */
	public Purchase show(int eid,int pid,int actid) throws Exception;
	/**
	 * 实现对申请单的审核操作
	 * @param eid  审核人的编号
	 * @param pid  申请单的编号
	 * @param status  审核状态
	 * @return
	 * @throws Exception
	 */
	public boolean editStatus(int eid,int pid,int status) throws Exception;
}



























