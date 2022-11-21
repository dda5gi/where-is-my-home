package com.ssafy.home.qna.service;

import com.ssafy.home.qna.dto.QnaDto;

public interface QnaService {
	public int writeQuestion(QnaDto qnaDto) throws Exception;
	public int writeAnswer(QnaDto qnaDto) throws Exception;
}
