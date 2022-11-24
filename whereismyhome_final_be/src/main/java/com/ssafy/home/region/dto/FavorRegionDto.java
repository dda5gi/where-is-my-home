package com.ssafy.home.region.dto;

public class FavorRegionDto {
	private String memberId;
	private String dongCode;
	private String regionName;
	public FavorRegionDto() {
		super();
	}

	public FavorRegionDto(String memberId, String dongCode, String regionName) {
		super();
		this.memberId = memberId;
		this.dongCode = dongCode;
		this.regionName = regionName;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString() {
		return "FavorRegionDto [memberId=" + memberId + ", dongCode=" + dongCode + ", regionName=" + regionName + "]";
	}
	
}
