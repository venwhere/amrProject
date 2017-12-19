package com.huang.microboot.service;
import java.util.Map;
import com.huang.microboot.vo.Member;
public interface IMemberService {
	public Member getByMid(String mid);
	public Map<String,Object> listAuthByMid(String mid);
}
