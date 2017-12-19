package com.sun.amr.service.impl;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.sun.amr.dao.IResDAO;
import com.sun.amr.service.IResService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Page;
@Component
public class ResServiceImpl extends AbstractService implements IResService {
	@Resource
	private IResDAO resDAO;
	@Override
	public Map<String, Object> list(String column, String keyWord, int currentPage, int lineSize) throws Exception {
		Map<String, Object> map=new HashMap<String,Object>();
		//查询办公用品列表信息
		map.put("allRess", this.resDAO.findAllSplit(super.listParamMap(column, keyWord, currentPage, lineSize)));
		//数据量
		Integer count=this.resDAO.getAllCount(super.countParamMap(column, keyWord));
		//计算总页数
		Integer allPages=count/lineSize+(count%lineSize==0?0:1);
		Page page=new Page(currentPage, allPages);
		page.setCount(count);
		//将分页信息保存
		map.put("pager", page);
		return map;
	}
}















