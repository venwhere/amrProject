package com.huang.microboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
@SpringBootApplication
public class ShiroWebProviderSpringBootStartClass extends SpringBootServletInitializer{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ShiroWebProviderSpringBootStartClass.class, args);
    }
}