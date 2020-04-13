package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.dto.ResumeHiredDto;
import com.BYS.GWSystem.model.Resume;

@Mapper
public interface ResumeMapper {
	// 查询岗位
	public List<Resume> selectResumeList();

	// 更改
	public int updateResume(ResumeDto resume);

	// 添加
	public int insertResume(ResumeDto resume);

	// 查询简历是否存在
	public ResumeDto queryResumeById(String studentId);

	// 删除
	public int deleteResume(Resume resume);

	// 查询已编辑简历学生数量
	public Integer selectEditedResumeNumber();

	// 简历数量
	public Integer selectResumeNumber();

	// 被关注简历数量
	public Integer selectFollowNumber();

	// 被关注数>a的数目
	public Integer ConcernedAbout(@Param("large") Integer large, @Param("Small") Integer Small);

	// 根据id查询简历详情
	public ResumeDto selectResumeById(Long id);

	// 公司收到的投递的简历查询
	public List<ResumeHiredDto> selectResumeByErId(@Param(value = "registrationId") String registrationId);

	// 通过前两个ID确认是哪个简历并更新该简历(1被打回去2录用3表示备选4投递)
	public int updateHiredCollectionMsg(@Param(value = "studentId") String studentId,
			@Param(value = "postId") String postId, @Param(value = "updateCode") int updateCode);

	// 计算投递的简历数量，collection=4
	public Integer ResumeCount(@Param(value = "registrationId") String registrationId);

	// 计算通过的简历数量，collection=3
	public Integer ResumePassCount(@Param(value = "registrationId") String registrationId);

}
