package com.BYS.GWSystem.dto;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.BYS.GWSystem.dto")
public class CompanyHiredInfoDto {
	//岗位ID
	String postId;
	//父岗位ID
	String fatherTypeId;
	//招聘岗位名
	String postName;
	//岗位专业*
	String major;
	//工种
	String profession;
	//------------------------
	//工商注册号
	String registrationId;
	//岗位ID
	Integer typeId;
	//负责事项
	String responsible;
	//相关要求
	String demand;
	//招聘人数*
	Integer number;
	//已招人数
	Integer arrived;
	//薪资待遇
	String salary;
	
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getFatherTypeId() {
		return fatherTypeId;
	}
	public void setFatherTypeId(String fatherTypeId) {
		this.fatherTypeId = fatherTypeId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getArrived() {
		return arrived;
	}
	public void setArrived(Integer arrived) {
		this.arrived = arrived;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "CompanyHiredInfoDto [postId=" + postId + ", fatherTypeId=" + fatherTypeId + ", postName=" + postName
				+ ", major=" + major + ", profession=" + profession + ", registrationId=" + registrationId + ", typeId="
				+ typeId + ", responsible=" + responsible + ", demand=" + demand + ", number=" + number + ", arrived="
				+ arrived + ", salary=" + salary + "]";
	}
	public CompanyHiredInfoDto(String postId, String fatherTypeId, String postName, String major, String profession,
			String registrationId, Integer typeId, String responsible, String demand, Integer number, Integer arrived,
			String salary) {
		super();
		this.postId = postId;
		this.fatherTypeId = fatherTypeId;
		this.postName = postName;
		this.major = major;
		this.profession = profession;
		this.registrationId = registrationId;
		this.typeId = typeId;
		this.responsible = responsible;
		this.demand = demand;
		this.number = number;
		this.arrived = arrived;
		this.salary = salary;
	}
	public CompanyHiredInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
