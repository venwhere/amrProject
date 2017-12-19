package com.sun.amr.dao;
import java.util.List;
import java.util.Map;

public interface IDAO<K,V> {
	/**
	 * 实现数据的增加
	 * @param vo  要添加的vo对象数据
	 * @return   返回执行影响的行数
	 * @throws Exception
	 */
	public int doCreate(V vo) throws Exception;
	/**
	 * 实现数据的修改
	 * @param vo   要修改的vo数据对象
	 * @return  返回执行布尔值
	 * @throws Exception
	 */
	public int doUpdate(V vo) throws Exception;
	/**
	 * 实现数据的批量删除
	 * @param ids 保存了要删除的数据编号的集合
	 * @return   返回执行布尔值
	 * @throws Exception
	 */
	public int doRemoveBatch(List<K> ids) throws Exception;
	/**
	 * 根据编号查询数据
	 * @param id 要查询的数据的编号
	 * @return  返回查询的数据
	 * @throws Exception
	 */
	public V findById(K id) throws Exception;
	/**
	 * 查询所有数据
	 * @return   返回查询到的数据
	 * @throws Exception
	 */
	public List<V> findAll() throws Exception;
	/**
	 * 实现模糊分页查询
	 * @param column  模糊查询的字段
	 * @param keyWord 模糊查询的关键字
	 * @param currentPage  当前页
	 * @param lineSize   每页显示的数据量
	 * @return   返回当页要显示的数据
	 * @throws Exception
	 */
	public List<V> findAllSplit(String column,String keyWord,
			Integer currentPage,Integer lineSize) throws Exception;
	/**
	 * 统计数量
	 * @param column  模糊查询的字段
	 * @param keyWord  模糊查询的关键字
	 * @return   返回总数
	 * @throws Exception
	 */
	public Integer getAllCount(String column,String keyWord) throws Exception;
	/**
	 * 实现模糊分页查询
	 * @param paramMap  带有查询条件的map集合
	 * @return
	 * @throws Exception
	 */
	public List<V> findAllSplit(Map<String, Object> paramMap) throws Exception;
	/**
	 * 实现数据的统计
	 * @param paramMap   保存了查询条件的map集合
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCount(Map<String, Object> paramMap) throws Exception;
}












