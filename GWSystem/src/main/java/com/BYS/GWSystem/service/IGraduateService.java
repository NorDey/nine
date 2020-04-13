package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.dto.GraduateDto;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.model.StudentHistory;
import com.github.pagehelper.Page;

public interface IGraduateService {

	// 新增简历时，将简历Id插入学生表
	public int insertResumeId(String StudentId, long resumeId);

	// 查询学生投递的所有简历
	public List<StudentHistory> selectAllCV(String studentId);

	// 投递简历
	public int sendCV(String studentId, String postId);

	// 查询此简历是否已投递在此
	public StudentHistory selectCV(String studentId, String postId);

	// 根据学号查询学生信息
	public Graduate queryStudentById(String id);

	// 修改头像根据id
	public int updatePicByid(Graduate graduate);

	// 添加毕业生
	public int insertGraduate(Graduate graduate);

	// 修改毕业生个人信息
	public int updateGraduate(Graduate graduate);

	// 学生简历编辑率
	public Double StudentResumeEditingRate();

	// 未编辑简历学生
	public List<GraduateDto> selectNotFilled();

	public Page<GraduateDto> PageNotFilled();

	// 学生列表
	public List<GraduateDto> selectCheckingStudents();

	public Page<GraduateDto> PageCheckingStudents();

	// 模糊条件查询学生列表
	public List<GraduateDto> selectGraduateListByMore(String lookup);

	public Page<GraduateDto> PageGraduateListByMore(String lookup);

	// 简历最受欢迎学生列表
	public List<GraduateDto> selectBestResumeStudents();

	public Page<GraduateDto> PageBestResumeStudents();

}
