package com.ssafy.home.apt.dto;

public class AptDto {
	private String aptName;
	private String aptCode;
	private String dong;
	private String jibun;
	private String lat;
	private String lng;

	public AptDto() {
		super();
	}

	public AptDto(String aptName, String aptCode, String dong, String jibun, String lat, String lng) {
		super();
		this.aptName = aptName;
		this.aptCode = aptCode;
		this.dong = dong;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "AptDto [aptName=" + aptName + ", aptCode=" + aptCode + ", dong=" + dong + ", jibun=" + jibun + ", lat="
				+ lat + ", lng=" + lng + "]";
	}


}
