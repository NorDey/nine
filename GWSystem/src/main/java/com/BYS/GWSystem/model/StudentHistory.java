package com.BYS.GWSystem.model;

import java.io.Serializable;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Component;

@Component
public class StudentHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 学号
	private String studentId;

	// 岗位号
	private String postId;

	// 投递结果,1,2,3,4
	private String collection;

	// 投递时间
	private Data time;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public Data getTime() {
		return time;
	}

	public void setTime(Data time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "StudentHistory [studentId=" + studentId + ", postId=" + postId + ", collection=" + collection
				+ ", time=" + time + "]";
	}

	public StudentHistory(String studentId, String postId, String collection, Data time) {
		super();
		this.studentId = studentId;
		this.postId = postId;
		this.collection = collection;
		this.time = time;
	}

	public StudentHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

}
