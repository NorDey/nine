package com.BYS.GWSystem;

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.BYS.GWSystem.model.Enterprise;

@SpringBootApplication
public class GwSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GwSystemApplication.class, args);
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Enterprise.class);

		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			System.out.println("beanName: " + beanName);
		}
	}
	//解决spring boot项目中浏览器首次加载URL中多出jsessionid的问题
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
		SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
		sessionCookieConfig.setHttpOnly(true);
	}
}
