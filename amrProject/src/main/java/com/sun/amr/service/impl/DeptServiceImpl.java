package com.sun.amr.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.IDeptDAO;
import com.sun.amr.service.IDeptService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Dept;
@Service
public class DeptServiceImpl extends AbstractService implements IDeptService{
	@Resource
	private IDeptDAO deptDAO;
	@Override
	public List<Dept> list(Integer eid) throws Exception {
		if(!super.checkAuth(eid, 4)) {//验证当前登陆的用户用4号权限
			return null;
		}
		//如果有权限调用dao层的方法对数据进行查询
		return deptDAO.findAll();
	}
	@Override
	public boolean edit(Integer eid, Dept vo) throws Exception {
		if (super.checkAuth(eid, 7)) {//验证是否具有7号权限
			return this.deptDAO.doUpdate(vo)>0;
		}
		return false;
	}
}










