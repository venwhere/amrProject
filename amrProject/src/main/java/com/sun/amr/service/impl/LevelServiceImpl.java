package com.sun.amr.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.ILevelDAO;
import com.sun.amr.service.ILevelService;
import com.sun.amr.vo.Level;
@Service
public class LevelServiceImpl implements ILevelService{
	@Resource
	private ILevelDAO levelDAO;
	@Override
	public boolean checkSalary(double salary, int lid) throws Exception {
		//根据雇员的级别查询出对应的最高工资和最低工资信息
		Level lel=this.levelDAO.findById(lid);
		if(salary>=lel.getLosal()&&salary<=lel.getHisal()) {
			return true;
		}
		return false;
	}
}
