package com.sun.amr.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.IActionDAO;
import com.sun.amr.service.IActionService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Action;
@Service
public class ActionServiceImpl extends AbstractService implements IActionService{
	@Resource
	private IActionDAO actionDAO;
	@Override
	public List<Action> getAllByGroups(Integer eid, Integer gid) throws Exception {
		if (super.checkAuth(eid, 6)) {
			return this.actionDAO.findAllByGroups(gid);
		}
		return null;
	}
}














