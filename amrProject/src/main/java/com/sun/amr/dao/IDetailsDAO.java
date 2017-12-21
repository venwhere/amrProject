package com.sun.amr.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.sun.amr.vo.Details;
@Mapper
public interface IDetailsDAO extends IDAO<Integer, Details> {
	/**
	 * 实现待购清单的所有信息的查询
	 * @param eid   当前的用户编号
	 * @return  待购清单的对象集合
	 * @throws Exception
	 */
	public List<Details> findAllDetails(Integer eid) throws Exception;
	/**
	 * 根据清单的编号进行查询
	 * @param ids 保存了要删除的清单编号的集合
	 * @return   要删除的清单集合
	 * @throws Exception
	 */
	public List<Details> findAllByPhoto(List<Integer> ids) throws Exception;
	/**
	 * 实现待购清单的数量修改
	 * @param vo  包含了要修改的清单编号的和数量
	 * @return   返回执行状态值
	 * @throws Exception
	 */
	public int doUpdateAmount(Details vo) throws Exception;
	/**
	 * 实现修改前的数据回显
	 * @param eid  操作人员的编号
	 * @param id   清单的编号
	 * @return   返回清单详情集合
	 * @throws Exception
	 */
	public Details findByIdForPrebuy(Integer eid,Integer id) throws Exception;
	/**
	 * 实现清单的信息修改
	 * @param vo  包含了要修改的数据的vo对象
	 * @return  返回执行的状态值
	 * @throws Exception
	 */
	public int doUpdatePrebuy(Details vo) throws Exception;
	/**
	 * 实现清单的pid修改
	 * @param pid   自增长返回的申请单的编号
	 * @param eid   需要申请人和清单的填写人保持一致
	 * @return   执行的影响行数
	 * @throws Exception
	 */
	public int doUpdateByPurchase(Integer pid,Integer eid) throws Exception;
	/**
	 * 根据申请单编号查询所有详情
	 * @param pid  申请单编号
	 * @return
	 * @throws Exception
	 */
	public List<Details> findAllByPurchase(Integer pid) throws Exception;
	/**
	 * 实现判断要追加的待购清单是否存在
	 * @param eid  操作人的编号
	 * @param rid  对应的用品的编号
	 * @return
	 * @throws Exception
	 */
	public Details findByDetailsExists(Integer eid,Integer rid) throws Exception;
	/**
	 * 修改数量
	 * @param did
	 * @return
	 * @throws Exception
	 */
	public int doUpdateAppendAmount(int did) throws Exception;
}




















