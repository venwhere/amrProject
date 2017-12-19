package com.huang.microboot.test;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import com.huang.microboot.ProviderSpringBootStartClass;
import com.huang.microboot.vo.Member;
@SpringBootTest(classes=ProviderSpringBootStartClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestMember {
	@Resource
	private RestTemplate template;
	@Test
	public void testGetByMid() {
		System.err.println(this.template.postForObject("http://localhost:8080/member/get?mid=admin",null,Member.class ));
	}
	@Test
	public void testListAuthByMid() {
		Map<String,Object> map=this.template.postForObject("http://localhost:8080/member/auth?mid=admin",null,Map.class );
		System.err.println("角色有:"+map.get("allRoles"));
		System.err.println("权限有:"+map.get("allActions"));
	}

}


















