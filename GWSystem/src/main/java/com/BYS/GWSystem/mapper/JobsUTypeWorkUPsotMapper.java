package com.BYS.GWSystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.BYS.GWSystem.dto.*;

@Mapper
public interface JobsUTypeWorkUPsotMapper {
	//联表更新某一个招聘信息
	public int updateOneMSG(CompanyHiredInfoDto HiredInfo);
	//联表查询某一个招聘信息
	public CompanyHiredInfoDto searchOne(@Param(value="postId") String postId);

}
