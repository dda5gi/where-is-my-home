package com.ssafy.home.qna.service;

import java.util.List;

import com.ssafy.home.qna.dto.AnswerDto;
import com.ssafy.home.qna.dto.QuestionDto;

public interface QnaService {
	public int writeQuestion(QuestionDto questionDto) throws Exception;
	public int writeAnswer(AnswerDto answerDto) throws Exception;

	public List<QuestionDto> listQuestion() throws Exception;
	public List<AnswerDto> listAnswer(int articleNo) throws Exception;

	public QuestionDto getQuestion(int articleNo) throws Exception;
	public AnswerDto getAnswer(int articleNo) throws Exception;

	public int modifyQuestion(QuestionDto questionDto) throws Exception;
	public int modifyAnswer(AnswerDto answerDto) throws Exception;

	public int deleteQuestion(int articleNo) throws Exception;
	public int deleteAnswer(int articleNo) throws Exception;

	public int updateHitQuestion(int articleNo) throws Exception;
	public int updateHitAnswer(int articleNo) throws Exception;
}