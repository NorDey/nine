package com.BYS.GWSystem.dto;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
@ComponentScan(value = "com.BYS.GWSystem.dto")
public class TypeWorkUJobs {
	
	List<String> postName;
	
	String pros;
	public List<String> getPostName() {
		return postName;
	}
	public void setPostName(List<String> postName) {
		this.postName = postName;
	}
	public String getPros() {
		return pros;
	}
	public void setPros(String pros) {
		this.pros = pros;
	}
	@Override
	public String toString() {
		return "Type_workUJobs [postName=" + postName + ", pros=" + pros + "]";
	}
	public TypeWorkUJobs(List<String> postName, String pros) {
		super();
		this.postName = postName;
		this.pros = pros;
	}
	public TypeWorkUJobs() {
		super();
		// TODO Auto-generated constructor stub
	}

}
