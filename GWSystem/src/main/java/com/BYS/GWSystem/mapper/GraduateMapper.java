package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.BYS.GWSystem.dto.GraduateDto;
import com.BYS.GWSystem.model.Graduate;

@Mapper
public interface GraduateMapper {
	
		//查询学生
		public List<Graduate> selectGraduateList();
		
		//更改
		public int updateGraduate(Graduate graduate);
		
		//添加
		public int insertGraduate(Graduate graduate);
		
		//删除
		public int deleteGraduate(Graduate graduate);

		//查询学生数量
		public int selectGraduateNumber();

		//未编辑简历学生
		public List<Graduate> selectNotFilledStudent();

		//学生列表
		public List<GraduateDto> selectCheckingStudents();

		//最受欢迎学生列表
		public List<GraduateDto> selectBestResumeStudents();
		
		

}
