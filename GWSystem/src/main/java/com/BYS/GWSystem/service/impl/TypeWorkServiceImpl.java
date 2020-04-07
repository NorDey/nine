package com.BYS.GWSystem.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.dto.TypeWorkUJobs;
import com.BYS.GWSystem.mapper.TypeWorkMapper;
import com.BYS.GWSystem.service.ITypeWorkService;

@Service
public class TypeWorkServiceImpl implements ITypeWorkService {
	
	@Autowired
	private TypeWorkMapper TWMapper;
	// 查询岗位的父类信息
	@Override
	public List<TypeWorkUJobs> AllPros() {
		// TODO Auto-generated method stub
		List<TypeWorkUJobs> tList = new  ArrayList<TypeWorkUJobs>();
		String profession=null;
		for (Iterator iterator = TWMapper.allPros().iterator(); iterator.hasNext();) {//循环所有的profession等同于fatherid
			TypeWorkUJobs  t = new TypeWorkUJobs();
			profession = (String) iterator.next();
			t.setPros(profession);
			List<String> allPostName = TWMapper.allPostName(profession);
			t.setPostName(allPostName);
			/*
			 * for (Iterator iterator2 = allPostName.iterator(); iterator2.hasNext();) {
			 * String string = (String) iterator2.next(); System.out.println("");
			 * t.getPostName().add(string); }
			 */
			System.out.println(t.toString());
			tList.add(t);
		}
		return tList;
	}

}
