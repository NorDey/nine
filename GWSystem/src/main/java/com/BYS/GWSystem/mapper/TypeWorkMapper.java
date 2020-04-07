package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.BYS.GWSystem.model.TypeWork;
import com.github.pagehelper.Page;

@Mapper
public interface TypeWorkMapper  {
	
	// 查询岗位的父类profession信息
	List<String> allPros();
	// 根据父类查询岗位的具体postname信息
	List<String> allPostName(@Param(value = "pros")String pros);
}
