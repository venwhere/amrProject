package com.sun.amr.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.IActionDAO;
import com.sun.amr.dao.IEmpDAO;
import com.sun.amr.dao.IGroupsDAO;
import com.sun.amr.dao.ILevelDAO;
import com.sun.amr.service.IAdminService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Action;
import com.sun.amr.vo.Emp;
import com.sun.amr.vo.Groups;
import com.sun.amr.vo.Level;
import com.sun.amr.vo.Page;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class AdminServiceImpl extends AbstractService implements IAdminService {
	@Resource
	private IGroupsDAO groupsDAO;
	@Resource
	private IActionDAO actionDAO;
	@Resource
	private IEmpDAO empDAO;
	@Resource
	private ILevelDAO levelDAO;

	@Override
	public Emp login(Emp vo) throws Exception {
		Emp rvo=this.empDAO.findLogin(vo);
		if(rvo!=null) {
			vo.setName(rvo.getName());
			vo.setPhoto(rvo.getPhoto());
			vo.setAflag(rvo.getAflag());
			vo.setDept(rvo.getDept());
			vo.setLevel(rvo.getLevel());
			vo.setPassword(rvo.getPassword());
			List<Groups> allGroups=this.groupsDAO.findAllByDept(vo.getDept().getDid());
			Iterator<Groups> iter=allGroups.iterator();
			while(iter.hasNext()) {
				Groups g=iter.next();
				g.setAllActions(this.actionDAO.findAllByGroups(g.getGid()));
			}
			vo.getDept().setAllGroups(allGroups);
			return vo;
		}
		return null;
	}

	public Map<String, Object> auth(Emp vo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<String> allRoles = new HashSet<String>();
		Set<String> allActions = new HashSet<String>();
		System.err.println("vo.getDept().getDid()======="+vo.getDept().getDid());
		List<Groups> allGroups = this.groupsDAO.findAllByDept(vo.getDept().getDid());
		Iterator<Groups> iter = allGroups.iterator();
		while (iter.hasNext()) {
			Groups g = iter.next();
			allRoles.add(g.getGid().toString());
			List<Action> allActionsRes = this.actionDAO.findAllByGroups(g.getGid());
			Iterator<Action> iteAction=allActionsRes.iterator();
			while(iteAction.hasNext()) {
				allActions.add(iteAction.next().toString());
			}
		}
		map.put("allRoles",allRoles);
		map.put("allActions", allActions);
		return map;
	}

	@Override
	public Map<String, Object> addPre() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allLevels", this.levelDAO.findAll());
		return map;
	}

	@Override
	public boolean checkEid(int eid) throws Exception {
		return this.empDAO.findById(eid) == null;
	}

	@Override
	public boolean add(Emp vo, Integer eid) throws Exception {
		if (!super.checkAuth(eid, 2)) {// 调用父类的方法进行权限验证
			System.out.println("(!super.checkAuth(eid,2))");
			return false;
		}
		// 判断输入的雇员编号是否已经存在，在客户端的表单中远程验过一次，这里再次验证是防止恶意避开表单在地址栏输入参数放直接发送请求
		if (this.empDAO.findById(vo.getEid()) != null) {// 该雇员已经存在
			System.out.println("(this.empDAO.findById(vo.getEid())!=null)");
			return false;
		}
		// 判断输入用户的工资是否正确，在客户端的表单中远程验过一次，这里再次验证是防止恶意避开表单在地址栏输入参数放直接发送请求
		Level lev = this.levelDAO.findById(vo.getLevel().getLid());
		if (vo.getSalary() < lev.getLosal() || vo.getSalary() > lev.getHisal()) {
			return false;
		}
		return this.empDAO.doCreate(vo) > 0;
	}

	@Override
	public Map<String, Object> list(int eid, String column, String keyWord, int currentPage, int lineSize)
			throws Exception {
		if (!super.checkAuth(eid, 3)) {// 权限的验证
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		// 调用父类的方法将查询参数保存到map之后再传递到dao层的方法
		map.put("allEmps", this.empDAO.findAllAdmin(super.listParamMap(column, keyWord, currentPage, lineSize)));
		// 统计总数据量
		Integer count = this.empDAO.getAllAdminCount(super.countParamMap(column, keyWord));
		// 创建分页信息
		Integer allPages = count / lineSize + (count % lineSize == 0 ? 0 : 1);
		// 创建分页信息
		Page page = new Page(currentPage, allPages);
		// 将数据的总条数保存到page中
		page.setCount(count);
		map.put("pager", page);
		return map;
	}
}
