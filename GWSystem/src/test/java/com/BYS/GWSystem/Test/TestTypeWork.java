package com.BYS.GWSystem.Test;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.BYS.GWSystem.GwSystemApplication;
import com.BYS.GWSystem.dto.TypeWorkUJobs;
import com.BYS.GWSystem.mapper.TypeWorkMapper;
import com.BYS.GWSystem.service.ITypeWorkService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GwSystemApplication.class)
class TestTypeWork {
	private TypeWorkMapper TWMapper;
	private Service Service;
	@Resource
	private ITypeWorkService tService;
	
	public void before() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "spring-mvc.xml", "spring-mybatis.xml" });
		Service = (Service) ac.getBean("Service");
	}

	@Test
	void testTestAllPros() {
		List<TypeWorkUJobs> allPros = tService.AllPros();
		for (Iterator iterator = allPros.iterator(); iterator.hasNext();) {
			TypeWorkUJobs typeWorkUJobs = (TypeWorkUJobs) iterator.next();
			System.out.println("-------------------"+typeWorkUJobs.getPros());
			List<String> postName = typeWorkUJobs.getPostName();
			for (Iterator iterator2 = postName.iterator(); iterator2.hasNext();) {
				String string = (String) iterator2.next();
				System.out.println("岗位：+++++++++"+string);
			}
		}

	}

}
