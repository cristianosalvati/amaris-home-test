package com.amaris.hometest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.amaris.hometest.util.TransactionLogInterceptor;

@SpringBootApplication
@ComponentScan(basePackages = "com.amaris.hometest")
public class AmarisHomeTestApplication implements WebMvcConfigurer{

	@Autowired
	private final TransactionLogInterceptor transactionLogInterceptor = new TransactionLogInterceptor(); 
	
	public static void main(String[] args) {
		SpringApplication.run(AmarisHomeTestApplication.class, args);	
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(transactionLogInterceptor).addPathPatterns("/rest/**"	);
    }
	
}
