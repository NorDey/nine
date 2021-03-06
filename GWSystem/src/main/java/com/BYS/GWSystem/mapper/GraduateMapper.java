package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.BYS.GWSystem.dto.GraduateDto;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.model.StudentHistory;

@Mapper
public interface GraduateMapper {

	// 新增简历时，将简历Id插入学生表
	public int insertResumeId(@Param("StudentId")String StudentId,@Param("resumeId") long resumeId);

	// 查询学生投递的所有简历
	public List<StudentHistory> selectAllCV(String studentId);

	// 首次投递简历，insert
	public int sendCV(String studentId, String postId);

	// 查询此简历是否已投递在此
	public StudentHistory selectCV(String studentId, String postId);

	// 根据学号查询学生信息
	public Graduate queryStudentById(String id);

	// 修改头像根据id
	public int updatePicByid(Graduate graduate);

	// 查询学生
	public List<Graduate> selectGraduateList();

	// 更改
	public int updateGraduate(Graduate graduate);

	// 添加
	public int insertGraduate(Graduate graduate);

	// 删除
	public int deleteGraduate(Graduate graduate);

	// 查询学生数量
	public int selectGraduateNumber();

	// 未编辑简历学生
	public List<GraduateDto> selectNotFilledStudent();

	// 学生列表
	public List<GraduateDto> selectCheckingStudents();

	// 最受欢迎学生列表
	public List<GraduateDto> selectBestResumeStudents();

	// 模糊条件查询学生列表
	public List<GraduateDto> selectGraduateListByMore(@Param(value = "lookup") String lookup);

}
