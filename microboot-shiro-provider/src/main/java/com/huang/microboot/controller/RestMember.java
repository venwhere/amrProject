package com.huang.microboot.controller;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.huang.microboot.service.IMemberService;
@RestController
public class RestMember {
	@Resource
	private IMemberService memberService;
	@RequestMapping(value="/member/get",method=RequestMethod.POST)
	public Object getMember(String mid) {
		return this.memberService.getByMid(mid);
	}
	@RequestMapping(value="/member/auth",method=RequestMethod.POST)
	public Object getAuth(String mid) {
		return this.memberService.listAuthByMid(mid);
	}
}
















