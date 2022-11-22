package com.ssafy.home.apt.dto;

public class AptDto {
	private String aptName;
	private int floor;
	private String dong;
	private String jibun;
	private float area;
	private String lat;
	private String lng;

	public AptDto() {
		super();
	}

	public AptDto(String aptName, int floor, String dong, String jibun, float area, String lat, String lng) {
		super();
		this.aptName = aptName;
		this.floor = floor;
		this.dong = dong;
		this.jibun = jibun;
		this.area = area;
		this.lat = lat;
		this.lng = lng;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
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

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
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
		return "AptDto [aptName=" + aptName + ", floor=" + floor + ", dong=" + dong + ", jibun=" + jibun + ", area="
				+ area + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}
