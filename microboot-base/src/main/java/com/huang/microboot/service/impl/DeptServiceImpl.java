package com.huang.microboot.service.impl;
import java.util.List;
import javax.annotation.Resource;
import com.huang.microboot.dao.IDeptDAO;
import com.huang.microboot.service.IDeptService;
import com.huang.microboot.vo.Dept;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class DeptServiceImpl implements IDeptService{
	@Resource
	private IDeptDAO deptDAO;
	@Override
	public List<String> list() {
		return this.deptDAO.findAll();
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean add(Dept vo) {
		return this.deptDAO.doCreate(vo)>0;
	}
}
