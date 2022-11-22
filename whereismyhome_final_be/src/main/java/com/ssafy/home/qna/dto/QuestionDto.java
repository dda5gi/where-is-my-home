package com.ssafy.home.qna.dto;

public class QuestionDto {
	private int articleNo;
	private String title;
	private String content;
	private String registerTime;
	private int hit;
	private String memberId;

	public QuestionDto() {
		super();
	}

	public QuestionDto(int articleNo, String title, String content, String registerTime, int hit, String memberId) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.content = content;
		this.registerTime = registerTime;
		this.hit = hit;
		this.memberId = memberId;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "QuestionDto [articleNo=" + articleNo + ", title=" + title + ", content=" + content + ", registerTime="
				+ registerTime + ", hit=" + hit + ", memberId=" + memberId + "]";
	}

}