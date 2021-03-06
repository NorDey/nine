package com.BYS.GWSystem.dto;

public class GraduateDto {
	    /**
	     * 学号
	     */
	    private String studentId;

	    /**
	     * 姓名
	     */
	    private String studentName;

	    /**
	     * 性别(1男0女)
	     */
	    private String sex;

	    /**
	     * 电话
	     */
	    private String phonenumber;

	    /**
	     * 家庭住址
	     */
	    private String homeAddress;

	    /**
	     * 头像路径
	     */
	    private String avatarPath;

	    /**
	     * 毕业去向
	     */
	    private String whereabouts;

	    /**
	     * 就业情况(1已就业0未就业)
	     */
	    private String cause;

	    /**
	     * 工作岗位
	     */
	    private String post;

	    /**
	     * 就业单位
	     */
	    private String company;

	    /**
	     * 简历id
	     */
	    private Long resumeId;


	    
	    
	    public GraduateDto(String studentId, String studentName, String sex, String phonenumber, String homeAddress,
				String avatarPath, String whereabouts, String cause, String post, String company, Long resumeId) {
			super();
			this.studentId = studentId;
			this.studentName = studentName;
			this.sex = sex;
			this.phonenumber = phonenumber;
			this.homeAddress = homeAddress;
			this.avatarPath = avatarPath;
			this.whereabouts = whereabouts;
			this.cause = cause;
			this.post = post;
			this.company = company;
			this.resumeId = resumeId;
		}

		public GraduateDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getStudentId() {
	        return studentId;
	    }

	    public void setStudentId(String studentId) {
	        this.studentId = studentId;
	    }

	    public String getStudentName() {
	        return studentName;
	    }

	    public void setStudentName(String studentName) {
	        this.studentName = studentName;
	    }

	    public String getSex() {
	        return sex;
	    }

	    public void setSex(String sex) {
	        this.sex = sex;
	    }

	    public String getPhonenumber() {
	        return phonenumber;
	    }

	    public void setPhonenumber(String phonenumber) {
	        this.phonenumber = phonenumber;
	    }

	    public String getHomeAddress() {
	        return homeAddress;
	    }

	    public void setHomeAddress(String homeAddress) {
	        this.homeAddress = homeAddress;
	    }

	    public String getAvatarPath() {
	        return avatarPath;
	    }

	    public void setAvatarPath(String avatarPath) {
	        this.avatarPath = avatarPath;
	    }

	    public String getWhereabouts() {
	        return whereabouts;
	    }

	    public void setWhereabouts(String whereabouts) {
	        this.whereabouts = whereabouts;
	    }

	    public String getCause() {
	        return cause;
	    }

	    public void setCause(String cause) {
	        this.cause = cause;
	    }

	    public String getPost() {
	        return post;
	    }

	    public void setPost(String post) {
	        this.post = post;
	    }

	    public String getCompany() {
	        return company;
	    }

	    public void setCompany(String company) {
	        this.company = company;
	    }

	    public Long getResumeId() {
	        return  resumeId;
	    }

	    public void setResumeId(Long resumeId) {
	        this.resumeId = resumeId;
	    }

	    @Override
	    public String toString() {
	        return "Graduate{" +
	        "studentId=" + studentId +
	        ", studentName=" + studentName +
	        ", sex=" + sex +
	        ", phonenumber=" + phonenumber +
	        ", homeAddress=" + homeAddress +
	        ", avatarPath=" + avatarPath +
	        ", whereabouts=" + whereabouts +
	        ", cause=" + cause +
	        ", post=" + post +
	        ", company=" + company +
	        ", resumeId=" + resumeId +
	        "}";
	    }
}
