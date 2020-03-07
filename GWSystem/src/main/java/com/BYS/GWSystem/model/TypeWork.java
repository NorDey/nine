package com.BYS.GWSystem.model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxx
 * @since 2020-03-07
 */
public class TypeWork implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位id
     */
    private String postId;

    /**
     * 父岗位id
     */
    private String fatherTypeId;

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

    
    

    public TypeWork() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeWork(String postId, String fatherTypeId, String postName, String profession, String major) {
		super();
		this.postId = postId;
		this.fatherTypeId = fatherTypeId;
		this.postName = postName;
		this.profession = profession;
		this.major = major;
	}

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

    @Override
    public String toString() {
        return "TypeWork{" +
        "postId=" + postId +
        ", fatherTypeId=" + fatherTypeId +
        ", postName=" + postName +
        ", profession=" + profession +
        ", major=" + major +
        "}";
    }
}
