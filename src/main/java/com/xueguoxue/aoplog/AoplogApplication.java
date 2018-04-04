package com.xueguoxue.aoplog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.xueguoxue.aoplog")
@EnableCaching
public class AoplogApplication {

	public static void main(String[] args) {
		SpringApplication.run(AoplogApplication.class, args);
	}
}
