package com.BYS.GWSystem.model;
import java.io.Serializable;

import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxx
 * @since 2020-03-07
 */
@ComponentScan(value = "com.BYS.GWSystem.model")
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //工商注册号
    private String registrationId;

    //企业全名
    private String enterpriseName;

    //企业联系方式
    private String telephone;

    //密码
    private String password;

    //企业人数
    private String number;

    //头像路径
    private String avatarPath;

    //公司简介
    private String synopsis;

    //企业地址
    private String address;

    //企业注册资本
    private String registeredCapital;

    //企业是否上市(1已上市0未上市)
    private Integer beListed;

    //是否通过审查(1申请中,2通过,3未通过)
    private Integer examination;


    
    public Enterprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enterprise(String registrationId, String enterpriseName, String telephone, String password, String number,
			String avatarPath, String synopsis, String address, String registeredCapital, Integer beListed,
			Integer examination) {
		super();
		this.registrationId = registrationId;
		this.enterpriseName = enterpriseName;
		this.telephone = telephone;
		this.password = password;
		this.number = number;
		this.avatarPath = avatarPath;
		this.synopsis = synopsis;
		this.address = address;
		this.registeredCapital = registeredCapital;
		this.beListed = beListed;
		this.examination = examination;
	}

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Integer getBeListed() {
        return beListed;
    }

    public void setBeListed(Integer beListed) {
        this.beListed = beListed;
    }

    public Integer getExamination() {
        return examination;
    }

    public void setExamination(Integer examination) {
        this.examination = examination;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
        "registrationId=" + registrationId +
        ", enterpriseName=" + enterpriseName +
        ", telephone=" + telephone +
        ", password=" + password +
        ", number=" + number +
        ", avatarPath=" + avatarPath +
        ", synopsis=" + synopsis +
        ", address=" + address +
        ", registeredCapital=" + registeredCapital +
        ", beListed=" + beListed +
        ", examination=" + examination +
        "}";
    }
}
