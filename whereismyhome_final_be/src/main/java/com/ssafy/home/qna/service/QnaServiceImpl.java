package com.ssafy.home.qna.service;

import java.util.List;

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

	@Override
	public List<QuestionDto> listQuestion() throws Exception {
		return qnaMapper.listQuestion();
	}

	@Override
	public List<AnswerDto> listAnswer(int articleNo) throws Exception {
		return qnaMapper.listAnswer(articleNo);
	}

	@Override
	public QuestionDto getQuestion(int articleNo) throws Exception {
		return qnaMapper.getQuestion(articleNo);
	}

	@Override
	public AnswerDto getAnswer(int articleNo) throws Exception {
		return qnaMapper.getAnswer(articleNo);
	}

	@Override
	public int modifyQuestion(QuestionDto questionDto) throws Exception {
		return qnaMapper.modifyQuestion(questionDto);
	}

	@Override
	public int modifyAnswer(AnswerDto answerDto) throws Exception {
		return qnaMapper.modifyAnswer(answerDto);
	}

	@Override
	public int deleteQuestion(int articleNo) throws Exception {
		return qnaMapper.deleteQuestion(articleNo);
	}

	@Override
	public int deleteAnswer(int articleNo) throws Exception {
		return qnaMapper.deleteAnswer(articleNo);
	}

	@Override
	public int updateHitQuestion(int articleNo) throws Exception {
		return qnaMapper.updateHitQuestion(articleNo);
	}

	@Override
	public int updateHitAnswer(int articleNo) throws Exception {
		return qnaMapper.updateHitAnswer(articleNo);
	}
}
