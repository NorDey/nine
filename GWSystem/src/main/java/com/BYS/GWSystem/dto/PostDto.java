package com.BYS.GWSystem.dto;

import java.time.LocalDateTime;

public class PostDto {

	  /**
     * 工商注册号
     */
    private String registrationId;
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
     * 企业全名
     */
    private String enterpriseName;
    
    /**
     *
     * 岗位id
     */
    private String postId;   

    /**
     * 岗位类型名
     */
    private String postName;
    
    /**
     * 工种
     */
    private String profession;

    /**
     * 岗位专业
     */
    private String major;
    
    /**
     * 负责事项
     */
    private String responsible;

    /**
     * 岗位类型id
     */
    private String typeId;


    /**
     * 招聘人数
     */
    private String number;

    /**
     * 薪资待遇(*k)
     */
    private String salary;

    /**
     * 相关要求
     */
    private String demand;
    
    /**
     * 企业地址
     */
    private String address;
    
    /**
     * 发布时间
     */
    private LocalDateTime creationTime;

    /**
     * 是否热门(1正常,2热门)
     */
    private Integer popular;

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public Integer getPopular() {
		return popular;
	}

	public void setPopular(Integer popular) {
		this.popular = popular;
	}

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
}
