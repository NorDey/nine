package com.BYS.GWSystem;

import org.hibernate.validator.internal.metadata.raw.BeanConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.BYS.GWSystem.model.Enterprise;

@SpringBootApplication
public class GwSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwSystemApplication.class, args);
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Enterprise.class);

		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			System.out.println("beanName: " + beanName);
		}
	}

}
