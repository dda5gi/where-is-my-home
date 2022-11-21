package com.ssafy.home.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.qna.dto.QnaDto;
import com.ssafy.home.qna.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {

	QnaMapper qnaMapper;

	@Autowired
	public QnaServiceImpl(QnaMapper qnaMapper) {
		this.qnaMapper = qnaMapper;
	}

	@Override
	public int writeQuestion(QnaDto qnaDto) throws Exception {
		return qnaMapper.writeQuestion(qnaDto);
	}

	@Override
	public int writeAnswer(QnaDto qnaDto) throws Exception {
		return qnaMapper.writeAnswer(qnaDto);
	}
}
