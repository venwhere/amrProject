package com.huang.microboot.config;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
@Configuration
public class DruidConfig {
	@Bean
	public ServletRegistrationBean getServletRegistrationBean() {
		ServletRegistrationBean bean=new ServletRegistrationBean(
				new StatViewServlet(),"/druid/*");
		bean.addInitParameter("allow","127.0.0.1,192.168.232.1");
		bean.addInitParameter("deny","192.168.180.223");
		bean.addInitParameter("loginUsername","huang");
		bean.addInitParameter("loginPassword","123456");
		bean.addInitParameter("resetEnable","false");
		return bean;
	}
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean() {
		FilterRegistrationBean bean=new FilterRegistrationBean();
		bean.addUrlPatterns("/*");
		bean.setFilter(new WebStatFilter());
		bean.addInitParameter("exclusions","*.jpg,*.js,*.css,*.png,*.ico,/druid/*");
		return bean;
	}
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource getDataSource() {
		return new DruidDataSource();
	}
	
}























