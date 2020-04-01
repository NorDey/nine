package com.BYS.GWSystem.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Graduate;

import ch.qos.logback.core.util.FileUtil;

@Controller
@RequestMapping("/graduate")
public class GraduateController {

	@GetMapping("/home") // 毕业生主页
	public String home() {
		return "graduate/GraduateHome";
	}

	@GetMapping("/updateInfo") // 修改信息
	public String updateInfo() {
		return "graduate/UpdateInfo";
	}

	@GetMapping("/login") // 登录
	public String login() {
		return "graduate/Register";
	}

	@GetMapping("/register") // 注册
	public String register() {
		return "graduate/Register";
	}

	@PostMapping("/uploadPictures") // 上传毕业生头像
	public String uploadPictures(@RequestParam(value = "avatar_file") MultipartFile file,
			@ModelAttribute Graduate graduate, Model model,HttpServletRequest request) {
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\headportrait";
		String name = file.getOriginalFilename();  //图片名字
		path=path+"\\"+name;
		try {
			file.transferTo(new File(path));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "graduate/updateInfo";
	}
}
