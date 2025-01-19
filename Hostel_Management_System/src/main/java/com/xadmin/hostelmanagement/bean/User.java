package com.xadmin.hostelmanagement.bean;

public class User {

	String name;
	String phone_no;
	String email;
	int id;
	String password;
	public String userType;
	boolean currentStatus;


	public boolean isCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(boolean currentStatus) {
		this.currentStatus = currentStatus;
	}

	public User(String name, String phone_no, String email, int id, String password, String userType,
			boolean currentStatus) {
		super();
		this.name = name;
		this.phone_no = phone_no;
		this.email = email;
		this.id = id;
		this.password = password;
		this.userType = userType;
		this.currentStatus = currentStatus;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public User(int id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	public User(int id) {
		super();
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String name, String phone_no, String email, int id, String password, String userType) {
		super();
		this.name = name;
		this.phone_no = phone_no;
		this.email = email;
		this.id = id;
		this.password = password;
		this.userType = userType;
	}
	public User(String name, String phone_no, String email, String password , String userType) {
		super();
		this.name = name;
		this.phone_no = phone_no;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}


}
