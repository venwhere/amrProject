package com.huang.microboot.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class UploadConfig {
	@Bean
	public MultipartConfigElement getMulpart() {
		MultipartConfigFactory factory=new MultipartConfigFactory();
		factory.setMaxFileSize("50MB");
		factory.setMaxRequestSize("100MB");
		factory.setLocation("/");
		return factory.createMultipartConfig();
	}
}
