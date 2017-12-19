package com.huang.microboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@ImportResource(locations= {"classpath:applicationContext.xml"})
@EnableTransactionManagement
public class SpringBootStartClass extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootStartClass.class);
	}
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootStartClass.class, args);
    }
}