package com.ssafy.home.qna.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.qna.dto.AnswerDto;
import com.ssafy.home.qna.dto.QuestionDto;

@Mapper
public interface QnaMapper{
	public int writeQuestion(QuestionDto questionDto) throws SQLException;
	public int writeAnswer(AnswerDto answerDto) throws SQLException;

	public List<QuestionDto> listQuestion() throws SQLException;
	public List<AnswerDto> listAnswer(int articleNo) throws SQLException;

	public QuestionDto getQuestion(int articleNo) throws SQLException;
	public AnswerDto getAnswer(int articleNo) throws SQLException;

	public int modifyQuestion(QuestionDto questionDto) throws SQLException;
	public int modifyAnswer(AnswerDto answerDto) throws SQLException;

	public int deleteQuestion(int articleNo) throws SQLException;
	public int deleteAnswer(int articleNo) throws SQLException;

	public int updateHitQuestion(int articleNo) throws SQLException;
	public int updateHitAnswer(int articleNo) throws SQLException;
}
