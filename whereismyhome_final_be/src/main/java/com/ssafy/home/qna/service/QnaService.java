package com.ssafy.home.qna.service;

import com.ssafy.home.qna.dto.AnswerDto;
import com.ssafy.home.qna.dto.QuestionDto;

public interface QnaService {
	public int writeQuestion(QuestionDto questionDto) throws Exception;
	public int writeAnswer(AnswerDto answerDto) throws Exception;
}
