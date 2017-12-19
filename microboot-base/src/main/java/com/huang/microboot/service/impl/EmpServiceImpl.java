package com.huang.microboot.service.impl;
import com.huang.microboot.service.IEmpService;
import org.springframework.stereotype.Service;
@Service
public class EmpServiceImpl implements IEmpService{
	@Override
	public boolean add() throws Exception {
		System.out.println("数据插入成功！！！");
		return true;
	}
	@Override
	public boolean update(long eid) throws Exception {
		System.out.println("数据修改成功！！！");
		return true;
	}
}
