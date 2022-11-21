package com.ssafy.home.qna.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.qna.dto.QnaDto;

@Mapper
public interface QnaMapper {
	public int writeQuestion(QnaDto qnaDto) throws SQLException;
	public int writeAnswer(QnaDto qnaDto) throws SQLException;
	public int getQna(QnaDto qnaDto) throws SQLException;

}
