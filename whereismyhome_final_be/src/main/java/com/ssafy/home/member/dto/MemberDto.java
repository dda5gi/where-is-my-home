package com.ssafy.home.member.dto;

public class MemberDto {
	String userid;
	String username;
	String userpwd;
	String token;

	public MemberDto() {
		super();
	}

	public MemberDto(String userid, String username, String userpwd, String token) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.token = token;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "MemberDto [userid=" + userid + ", username=" + username + ", userpwd=" + userpwd + ", token=" + token
				+ "]";
	}

}
