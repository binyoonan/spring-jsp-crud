package com.kopo.project1;

public class User {
	int idx;
	String id;
	String pwd;
	String userType;
	String name;
	String phone;
	String address;
	String created;
	String lastUpdated;
	
	User(){
	}
	
	User(int idx, String userType, String id, String pwd, String name, String phone, String address, String created, String lastUpdated) {
		this.idx = idx;
		this.userType = userType;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.created = created;
		this.lastUpdated = lastUpdated;
	}
	
	User(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

	User(String id, String pwd, String name, String phone, String address) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	
	public int getIdx() {
		return this.idx;
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public String getId() {
		return this.id;
	}

	public String getPwd() {
		return this.pwd;
	}

	public String getName() {
		return this.name;
	}

	public String getPhone() {
		return this.phone;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getCreated() {
		return this.created;
	}
	
	public String getLastUpdated() {
		return this.lastUpdated;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
}
