package com.ssafy.home.qna.dto;

public class AnswerDto {
	int articleNo;
	String title;
	String content;
	String registerTime;
	int hit;
	String memberId;
	int questionArticleNo;
	
	public AnswerDto() {
		super();
	}

	public AnswerDto(int articleNo, String title, String content, String registerTime, int hit, String memberId,
			int questionArticleNo) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.content = content;
		this.registerTime = registerTime;
		this.hit = hit;
		this.memberId = memberId;
		this.questionArticleNo = questionArticleNo;
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

	public int getQuestionArticleNo() {
		return questionArticleNo;
	}

	public void setQuestionArticleNo(int questionArticleNo) {
		this.questionArticleNo = questionArticleNo;
	}

	@Override
	public String toString() {
		return "AnswerDto [articleNo=" + articleNo + ", title=" + title + ", content=" + content + ", registerTime="
				+ registerTime + ", hit=" + hit + ", memberId=" + memberId + ", questionArticleNo=" + questionArticleNo
				+ "]";
	}

}