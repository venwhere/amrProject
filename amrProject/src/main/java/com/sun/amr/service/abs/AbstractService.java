package com.sun.amr.service.abs;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import com.sun.amr.dao.IActionDAO;
import com.sun.amr.dao.IEmpDAO;
import com.sun.amr.vo.Emp;
public abstract class AbstractService {
	@Resource
	private IEmpDAO empDAO;
	@Resource
	private IActionDAO actionDAO;
	public boolean checkAuth(int eid,int...actid) throws Exception{
		//出现当前雇员的信息
		Emp emp=this.empDAO.findById(eid);
		//如果是超级管理员2或者是普通管理员1就直接放行，表示有权限
		if(emp.getAflag().equals(1)||emp.getAflag().equals(2)) {
			return true;
		}
		//如果不是管理员则需要判断是否有权限
		for(int x:actid) {
			//只要有操作的权限中有一个不属于该用户，则返回false,不让其进行操作,通过当前的用户所在部门查询出所有权限
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("did",emp.getDept().getDid());
			map.put("actid", x);
			if(this.actionDAO.findByIdAndDept(map)==null) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 实现雇员权限和级别的验证
	 * @param eid  雇员的编号
	 * @param lid  级别
	 * @param did  部门编号
	 * @param actid  权限
	 * @return
	 * @throws Exception
	 */
	public boolean checkAuth1(int eid,int lid,int did,int...actid) throws Exception{
		//出现当前雇员的信息
		Emp emp=this.empDAO.findById(eid);
		//验证所在部门
		if (!emp.getDept().getDid().equals(did)) {
			return false;
		}
		//验证级别
		if (!emp.getLevel().getLid().equals(lid)) {
			return false;
		}
		//如果是超级管理员2或者是普通管理员1就直接放行，表示有权限
		if(emp.getAflag().equals(1)||emp.getAflag().equals(2)) {
			return true;
		}
		//如果不是管理员则需要判断是否有权限
		for(int x:actid) {
			//只要有操作的权限中有一个不属于该用户，则返回false,不让其进行操作,通过当前的用户所在部门查询出所有权限
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("did",emp.getDept().getDid());
			map.put("actid", x);
			if(this.actionDAO.findByIdAndDept(map)==null) {
				System.out.println("this.actionDAO.findByIdAndDept(map)==null");
				return false;
			}
		}
		return true;
	}
	/**
	 * 将查询条件保存到map集合中
	 * @param column  模糊查询的字段
	 * @param keyWord   模糊查询的关键字
	 * @param currentPage  当前页
	 * @param lineSize  每页显示的数据量
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listParamMap(String column,String keyWord,
			Integer currentPage,Integer lineSize) throws Exception{
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("column",column);
		if(keyWord!=null) {
			map.put("keyWord", "%"+keyWord+"%");
		}
		map.put("start", (currentPage-1)*lineSize);
		map.put("lineSize", lineSize);
		return map;
	}
	/**
	 * 将统计数据量的查询的条件保存到map中
	 * @param column   模糊查询的字段
	 * @param keyWord  模糊查询的关键字
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> countParamMap(String column,String keyWord) throws Exception{
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("column", column);
		if(keyWord!=null) {
			map.put("keyWord", "%"+keyWord+"%");
		}
		return map;
	}
}



















