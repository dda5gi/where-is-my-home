package com.ssafy.home.member.dto;

public class MemberDto {
	String id;
	String name;
	String pwd;
	String token;
	String type;

	public MemberDto() {
		super();
	}

	public MemberDto(String id, String name, String pwd, String token, String type) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.token = token;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", name=" + name + ", pwd=" + pwd + ", token=" + token + ", type=" + type + "]";
	}


}
