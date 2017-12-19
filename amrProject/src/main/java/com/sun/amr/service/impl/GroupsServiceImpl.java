package com.sun.amr.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.IGroupsDAO;
import com.sun.amr.service.IGroupsService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Groups;
@Service
public class GroupsServiceImpl extends AbstractService implements IGroupsService{
	@Resource 
	private IGroupsDAO groupsDAO;
	@Override
	public List<Groups> getAllByDept(Integer eid, Integer did) throws Exception {
		if (super.checkAuth(eid, 6)) {//必须有6号权限
			return this.groupsDAO.findAllByDept(did);
		}
		return null;
	}
}






























