package com.sun.amr.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.ISubtypeDAO;
import com.sun.amr.service.ISubtypeService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Subtype;
@Service
public class SubtypeServiceImpl extends AbstractService implements ISubtypeService{
	@Resource
	private ISubtypeDAO subtypeDAO;
	@Override
	public List<Subtype> list(int tid) throws Exception {
		return this.subtypeDAO.findAllByType(tid);
	}

}
