package com.huang.microboot.test;
import java.util.Date;

import javax.annotation.Resource;

import com.huang.microboot.SpringBootStartClass;
import com.huang.microboot.vo.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
@SpringBootTest(classes=SpringBootStartClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestRedis {
	@Resource(name="redisOne")
	private RedisTemplate<String,Object> writeTemplate;
	@Resource(name="redisTwo")
	private RedisTemplate<String,Object> readTemplate;
	@Test
	public void testGet() {
		System.out.println(this.readTemplate.opsForValue().get("emp"));
	}
	@Test
	public void testSet() {
		Emp emp=new Emp();
		emp.setAge(26);
		emp.setEid(011);
		emp.setName("李四");
		emp.setEmail("1119@qq.com");
		emp.setHiredate(new Date());
		emp.setSal(121212.0);
		this.writeTemplate.opsForValue().set("emp",emp);;
	}
}
















