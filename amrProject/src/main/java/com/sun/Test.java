package com.sun;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.amr.vo.Groups;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory=ctx.getBean("sqlSessionFactory",SqlSessionFactory.class);
		SqlSession session=sqlSessionFactory.openSession();
//		Emp emp=new Emp();
//		emp.setEid(6001);
//		emp.setPassword("1");
//		Emp vo=session.selectOne("findLogin",emp);
//		System.out.println(vo);
		
//		List<Action> action=session.selectList("findAllByGroups",2);
//		System.out.println(action);
		
		List<Groups> groups=session.selectList("findAllByDept",1);
		System.out.println(groups);
	}
}
