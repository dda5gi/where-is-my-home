package com.ssafy.home.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.qna.dto.AnswerDto;
import com.ssafy.home.qna.dto.QuestionDto;
import com.ssafy.home.qna.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {

	QnaMapper qnaMapper;

	@Autowired
	public QnaServiceImpl(QnaMapper qnaMapper) {
		this.qnaMapper = qnaMapper;
	}

	@Override
	public int writeQuestion(QuestionDto questionDto) throws Exception {
		return qnaMapper.writeQuestion(questionDto);
	}

	@Override
	public int writeAnswer(AnswerDto answerDto) throws Exception {
		return qnaMapper.writeAnswer(answerDto);
	}
}
