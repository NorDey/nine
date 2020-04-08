package com.BYS.GWSystem.model;
import java.time.LocalDateTime;
import java.io.Serializable;


public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    
    /**
     * 工商注册号
     */
    private String registrationId;
    
    
    /**
     *
     * 岗位id
     */
    private String postId;

   

    /**
     * 负责事项
     */
    private String responsible;

    /**
     * 岗位类型id
     */
    private String typeId;

    /**
     * 招聘岗位名
     */
    private String postName;

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
     * 发布时间
     */
    private LocalDateTime creationTime;

    /**
     * 是否热门(1正常,2热门,3设置热门)
     */
    private Integer popular;


    
    
    public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(String postId, String registrationId, String responsible, String typeId, String postName, String number,
			 String salary, String demand, LocalDateTime creationTime, Integer popular) {
		super();
		this.postId = postId;
		this.registrationId = registrationId;
		this.responsible = responsible;
		this.typeId = typeId;
		this.postName = postName;
		this.number = number;
		this.salary = salary;
		this.demand = demand;
		this.creationTime = creationTime;
		this.popular = popular;
	}

	public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
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

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
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

    @Override
    public String toString() {
        return "Post{" +
        "postId=" + postId +
        ", registrationId=" + registrationId +
        ", responsible=" + responsible +
        ", typeId=" + typeId +
        ", postName=" + postName +
        ", number=" + number +
        ", salary=" + salary +
        ", demand=" + demand +
        ", creationTime=" + creationTime +
        ", popular=" + popular +
        "}";
    }
}
