package com.sun.amr.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.ITypeDAO;
import com.sun.amr.service.ITypeService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Type;
@Service
public class TypeServiceImpl extends AbstractService implements ITypeService{
	@Autowired
	private ITypeDAO typeDAO;
	@Override
	public List<Type> list() throws Exception {
		return this.typeDAO.findAll();
	}
}
