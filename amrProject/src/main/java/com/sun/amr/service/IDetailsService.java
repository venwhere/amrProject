package com.sun.amr.service;
import java.util.List;
import java.util.Map;
import com.sun.amr.vo.Details;
public interface IDetailsService {
	/**
	 * 增加前的准备操作
	 * @param eid  雇员的编号(权限验证)
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addPre(int eid) throws Exception;
	/**
	 * 进行数据的插入操作
	 * @param vo  保存具体信息的vo类
	 * @param eid   雇员的编号（权限验证）
	 * @return
	 * @throws Exception
	 */
	public boolean add(Details vo,int eid) throws Exception;
	/**
	 * 实现待购清单的查询，调用数据层的findAllPrebuy()方法
	 * @param eid  当前用户的编号
	 * @return
	 * @throws Exception
	 */
	public List<Details> listDetails(int eid) throws Exception;
	/**
	 * 实现根据编号进行批量删除，调用了数据层的findAllByPhoto()和doRemoveBatch()
	 * @param eid  操作人的编号，权限验证
	 * @param ids   被删除的清单编号的集合
	 * @return   
	 * @throws Exception
	 */
	public Map<String, Object> rm(int eid,List<Integer> ids) throws Exception;
	/**
	 * 实现数量的修改，调用数据层的doUpdateAmount()和findAllByPhoto()方法
	 * @param eid   操作人的编号
	 * @param dinfo   保存的编号和数量：key是编号，value是数量
	 * @param ids  保存了要删除的清单的编号的集合
	 * @return  返回数据：
	 * 1、要被删除清单的信息，目的是返回控制层中进行对应的的照片删除
	 * 2、最终修改的标记：false或者true
	 * @throws Exception
	 */
	public Map<String, Object> editAmount(int eid,Map<Integer, Integer> dinfo,List<Integer> ids) 
			throws Exception;
	/**
	 * 实现修改前的数据回显，需要回显一级类别信息、二级信息、基本信息
	 * @param eid  操作人的编号
	 * @param did  清单的编号
	 * @return  返回的数据是一个Map集合，集合中的数据有：<br>
	 * <li>key="allTypes",value=ITypeDAO.findAll()</li>
	 * <li>key="allSubtypes",value=ISubTypeDAO.findAllByType()</li>
	 * <li>key="details",value=IDetailsDAO.findByIdForPrebuy</li>
	 * @throws Exception
	 */
	public Map<String, Object> editPre(int eid,int did) throws Exception;
	/**
	 * 实现清单的真正编辑，调用数据层的doUpdatePrebuy()
	 * @param eid  操作人员的编号
	 * @param vo  包含了修改信息的vo对象
	 * @return   返回执行的状态值
	 * @throws Exception
	 */
	public boolean edit(int eid,Details vo) throws Exception;
	/**
	 * 实现已有办公用品的追加购买处理操作
	 * @param eid  雇员编号
	 * @param rid  办公用品的编号
	 * @return
	 * @throws Exception
	 */
	public boolean addAppend(int eid,int rid) throws Exception;
}









