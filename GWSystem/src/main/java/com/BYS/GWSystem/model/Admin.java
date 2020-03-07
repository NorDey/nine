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
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private Long number;

    /**
     * 密码
     */
    private String password;


    
    public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Long number, String password) {
		super();
		this.number = number;
		this.password = password;
	}

	public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
        "number=" + number +
        ", password=" + password +
        "}";
    }
}
