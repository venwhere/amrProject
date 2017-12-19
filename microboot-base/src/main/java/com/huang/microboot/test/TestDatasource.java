package com.huang.microboot.test;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.huang.microboot.SpringBootStartClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes=SpringBootStartClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestDatasource {
	@Resource
	DataSource datasource;
	@Test
	public void testDatasource() {
		try {
			System.err.println(this.datasource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}






















