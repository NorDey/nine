package com.BYS.GWSystem.service.impl;


import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.mapper.PostMapper;
import com.BYS.GWSystem.model.Post;
import com.BYS.GWSystem.service.IPostService;


@Service
public class PostServiceImpl  implements IPostService {

	@Autowired
	private PostMapper postMapper;
	
	DecimalFormat formater = new DecimalFormat("#0.##");
	//招聘信息关注率
	public Double AttentionRateOfRecruitmentInformation() {
		int a=postMapper.selectPostNumber();//岗位数
		int b=postMapper.selectFollowNumber();//被关注数
		float totalPrice= ((float)b/a*100);
		return Double.parseDouble(formater.format(totalPrice));
	}

}
