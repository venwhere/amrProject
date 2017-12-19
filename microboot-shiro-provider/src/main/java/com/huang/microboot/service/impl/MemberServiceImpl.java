package com.huang.microboot.service.impl;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.huang.microboot.dao.IActionDAO;
import com.huang.microboot.dao.IMemberDAO;
import com.huang.microboot.dao.IRoleDAO;
import com.huang.microboot.service.IMemberService;
import com.huang.microboot.vo.Member;
@Service
public class MemberServiceImpl implements IMemberService{
	@Resource
	private IMemberDAO memberDAO;
	@Resource
	private IRoleDAO roleDAO;
	@Resource
	private IActionDAO actionDAO;
	@Override
	public Member getByMid(String mid) {
		return this.memberDAO.findById(mid);
	}
	@Override
	public Map<String, Object> listAuthByMid(String mid) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("allRoles",this.roleDAO.findAllRoleByMid(mid));
		map.put("allActions",this.actionDAO.findAllActionByMid(mid));
		return map;
	}

}
