package com.BYS.GWSystem.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
public class GraduateLoginAdapter implements WebMvcConfigurer {

	@Autowired
	private GraduateLoginInterceptor graduateLoginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 添加对用户是否登录的拦截器，并添加过滤项、排除项
		registry.addInterceptor(graduateLoginInterceptor).addPathPatterns("/graduate/**")
				.excludePathPatterns("/css/**", "/js/**", "/images/**")// 排除样式、脚本、图片等资源文件
				.excludePathPatterns("/Public/SwitchLogin.html")// 排除登录页面
				.excludePathPatterns("/graduate/login");// 排除登录链接
	}

}
