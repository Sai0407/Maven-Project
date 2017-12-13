package com.ts.us.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UserRegistrationDTO {

	private String name;
	private String govtRegID;
	private String password;
	private String conformPassword;
	private CommonsMultipartFile imagePath;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGovtRegID() {
		return govtRegID;
	}
	public void setGovtRegID(String govtRegID) {
		this.govtRegID = govtRegID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConformPassword() {
		return conformPassword;
	}
	public void setConformPassword(String conformPassword) {
		this.conformPassword = conformPassword;
	}
	public CommonsMultipartFile getImagePath() {
		return imagePath;
	}
	public void setImagePath(CommonsMultipartFile imagePath) {
		this.imagePath = imagePath;
	}
	
}
