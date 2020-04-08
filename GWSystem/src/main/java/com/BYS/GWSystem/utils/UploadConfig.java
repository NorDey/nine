package com.BYS.GWSystem.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class UploadConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 获取项目中储存头像的文件夹的绝对路径
		String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\headportrait\\";
		// 映射图片保存地址
		registry.addResourceHandler("/headportrait/**")
				.addResourceLocations("file:"+path);
	}
}
