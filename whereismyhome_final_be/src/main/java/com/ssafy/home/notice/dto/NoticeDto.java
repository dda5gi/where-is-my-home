package com.ssafy.home.notice.dto;

public class NoticeDto {
	int articleNo;
	String title;
	String content;
	String registerTime;
	int hit;

	public NoticeDto() {
		super();
	}

	public NoticeDto(int articleNo, String title, String content, String registerTime, int hit) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.content = content;
		this.registerTime = registerTime;
		this.hit = hit;
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

	@Override
	public String toString() {
		return "NoticeDto [articleNo=" + articleNo + ", title=" + title + ", content=" + content + ", registerTime="
				+ registerTime + ", hit=" + hit + "]";
	}
	
}
