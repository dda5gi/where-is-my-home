package com.ssafy.home.qna.dto;

public class QnaDto {
	int articleNo;
	String title;
	String content;
	String registerTime;
	int hit;
	String category;
	int qnaArticleNo;
	String memberId;

	public QnaDto() {
		super();
	}

	public QnaDto(int articleNo, String title, String content, String registerTime, int hit, String category,
			int qnaArticleNo, String memberId) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.content = content;
		this.registerTime = registerTime;
		this.hit = hit;
		this.category = category;
		this.qnaArticleNo = qnaArticleNo;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQnaArticleNo() {
		return qnaArticleNo;
	}

	public void setQnaArticleNo(int qnaArticleNo) {
		this.qnaArticleNo = qnaArticleNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "QnaDto [articleNo=" + articleNo + ", title=" + title + ", content=" + content + ", registerTime="
				+ registerTime + ", hit=" + hit + ", category=" + category + ", qnaArticleNo=" + qnaArticleNo
				+ ", memberId=" + memberId + "]";
	}

}
