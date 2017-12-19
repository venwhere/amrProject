package com.sun.amr.service.impl;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.IDeptDAO;
import com.sun.amr.dao.IEmpDAO;
import com.sun.amr.dao.ILevelDAO;
import com.sun.amr.service.IEmpService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Emp;
import com.sun.amr.vo.Level;
import com.sun.amr.vo.Page;
@Service
public class EmpServiceImpl extends AbstractService implements IEmpService{
	@Resource 
	private IEmpDAO empDAO;
	@Resource
	private ILevelDAO levelDAO;
	@Resource	
	private IDeptDAO deptDAO;
	@Override
	public Map<String, Object> addPre() throws Exception {
		Map<String, Object> map=new HashMap<String,Object>();
		//查询雇员的级别信息
		map.put("allLevels", this.levelDAO.findAll());
		//回显部门信息
		map.put("allDepts", this.deptDAO.findAllBySflag(0));
		return map;
	}
	@Override
	public boolean checkEid(int eid) throws Exception {
		//实现编号的远程验证
		return this.empDAO.findById(eid)==null;  
	}
	@Override
	public boolean add(Emp vo, Integer eid) throws Exception {
		if (!super.checkAuth(eid, 12)) {//需要12号权限
			return false;
		}
		//验证增加的雇员的编号是否已经存在，防止非表单的提交
		if (this.empDAO.findById(vo.getEid())!=null) {
			return false;
		}
		//如果增加雇员的编号是管理部门的编号则直接返回false，防止非表单的提交
		if (vo.getDept().getDid().equals(1)) {
			return false;
		}
		vo.setHeid(eid);//设置操作人的编号
		vo.setAflag(0);//设置雇员的特殊标记，标记为0则是普通员工，如果为1则为超级管理员，如果为2为普通管理员
		return this.empDAO.doCreate(vo)>0;
	}
	@Override
	public Map<String, Object> list(int eid, String column, String keyWord, int currentPage, int lineSize)
			throws Exception {
		if (!super.checkAuth(eid,13)) {    //权限验证，需要13号权限
			return null;
		}
		Map<String, Object> map=new HashMap<String,Object>();
		//将雇员的信息保存到map集合中
		map.put("allEmps", this.empDAO.findAllEmp(super.listParamMap(column, keyWord, currentPage, lineSize)));
		//取得员工记录总数
		Integer count=this.empDAO.getAllEmpCount(super.countParamMap(column, keyWord));
		//计算出总的页数
		Integer allPages=count/lineSize+(count%lineSize==0?0:1);
		Page page=new Page(currentPage,allPages);
		//将分页信息保存到map集合中
		map.put("pager", page);
		return map;
	}
	@Override
	public Map<String, Object> eidtPre(Integer eid) throws Exception {
		Map<String, Object> map=new HashMap<String,Object>();
		//查询员工的级别信息
		map.put("allLevels", this.levelDAO.findAll());
		//查询部门的信息
		map.put("allDepts", this.deptDAO.findAllBySflag(0));
		//雇员的基本信息
		map.put("emp", this.empDAO.findById(eid));
		return map;
	}
	@Override
	public boolean edit(Emp vo, Integer eid) throws Exception {
		if (!super.checkAuth(eid, 15)) {
			return false;
		}
		//不能修改为管理部门，防止绕过表单提交数据
		if (vo.getDept().getDid().equals(1)) {
			return false;
		}
		//验证工资范围
		Level lev=this.levelDAO.findById(vo.getLevel().getLid());
		if (vo.getSalary()>lev.getHisal()||vo.getSalary()<lev.getLosal()) {
			return false;
		}
		vo.setAflag(0);//将特殊标记设置为0
		return this.empDAO.doUpdate(vo)>0;
	}
}


















