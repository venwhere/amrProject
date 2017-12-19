package com.huang.microboot.test;
import java.util.List;
import javax.annotation.Resource;
import com.huang.microboot.SpringBootStartClass;
import com.huang.microboot.service.IDeptService;
import com.huang.microboot.vo.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
@SpringBootTest(classes=SpringBootStartClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestDept {
	@Resource
	private IDeptService deptService;
	@Test
	public void testList() {
		List<String> list=this.deptService.list();
		System.out.println(list);
	}
	@Test
	public void testAdd() {
		Dept vo=new Dept();
		vo.setTitle("嘻哈部");
		System.out.println(this.deptService.add(vo));
	}
}










