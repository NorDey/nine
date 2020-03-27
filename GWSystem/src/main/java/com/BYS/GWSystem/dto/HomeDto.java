package com.BYS.GWSystem.dto;

public class HomeDto {

	//学生简历编辑率
	public Double resumeRate;
	
	//岗位发布率
	public Double postReleaseRate;
	
	//招聘信息关注率
	public Double postAttentionRate;

	//简历关注率
	public Double resumeAttentionRate;
	
	//被关注＞5(如53.4)
	public Double T1;
	
	//被关注＞2(如53.4)
	public Double T2;
	
	//被关注＞0(如53.4)
	public Double T3;
	
	//被关注=0(如53.4)
	public Double T4;
	
	//未编辑简历(如53.4)
	public Double T5;
	

	public Double getResumeRate() {
		return resumeRate;
	}

	public void setResumeRate(Double resumeRate) {
		this.resumeRate = resumeRate;
	}

	public Double getPostReleaseRate() {
		return postReleaseRate;
	}

	public void setPostReleaseRate(Double postReleaseRate) {
		this.postReleaseRate = postReleaseRate;
	}

	public Double getPostAttentionRate() {
		return postAttentionRate;
	}

	public void setPostAttentionRate(Double postAttentionRate) {
		this.postAttentionRate = postAttentionRate;
	}

	public Double getResumeAttentionRate() {
		return resumeAttentionRate;
	}

	public void setResumeAttentionRate(Double resumeAttentionRate) {
		this.resumeAttentionRate = resumeAttentionRate;
	}

	public Double getT1() {
		return T1;
	}

	public void setT1(Double t1) {
		T1 = t1;
	}

	public Double getT2() {
		return T2;
	}

	public void setT2(Double t2) {
		T2 = t2;
	}

	public Double getT3() {
		return T3;
	}

	public void setT3(Double t3) {
		T3 = t3;
	}

	public Double getT4() {
		return T4;
	}

	public void setT4(Double t4) {
		T4 = t4;
	}

	public Double getT5() {
		return T5;
	}

	public void setT5(Double t5) {
		T5 = t5;
	}

	
	public HomeDto(Double resumeRate, Double postReleaseRate, Double postAttentionRate, Double resumeAttentionRate,
			Double t1, Double t2, Double t3, Double t4, Double t5) {
		super();
		this.resumeRate = resumeRate;
		this.postReleaseRate = postReleaseRate;
		this.postAttentionRate = postAttentionRate;
		this.resumeAttentionRate = resumeAttentionRate;
		T1 = t1;
		T2 = t2;
		T3 = t3;
		T4 = t4;
		T5 = t5;
	}

	public HomeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
