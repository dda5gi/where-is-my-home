package com.ssafy.home.qna;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.exception.UnAuthorizedException;
import com.ssafy.home.member.service.JwtService;
import com.ssafy.home.qna.dto.AnswerDto;
import com.ssafy.home.qna.dto.QuestionDto;
import com.ssafy.home.qna.service.QnaService;
import com.ssafy.home.response.ResponseDto;

@RestController
@RequestMapping("/qna")
public class QnaController {
	public static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnaService qnaService;
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/question")
	public ResponseEntity<ResponseDto> writeQuestion(@RequestBody QuestionDto questionDto, HttpServletRequest req) throws Exception {
		logger.info("질문 글 등록: {}", questionDto);
		ResponseDto responseDto = new ResponseDto();
		
		if(jwtService.checkToken(req.getHeader("access-token"))) {
			qnaService.writeQuestion(questionDto);
		} else {
			logger.error("사용 불가능 토큰!!!");
			throw new UnAuthorizedException();
		}
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/answer")
	public ResponseEntity<ResponseDto> writeAnswer(@RequestBody AnswerDto answerDto, HttpServletRequest req) throws Exception {
		logger.info("답변 글 등록: {}", answerDto);
		ResponseDto responseDto = new ResponseDto();
		
		if(jwtService.checkToken(req.getHeader("access-token"))) {
			qnaService.writeAnswer(answerDto);
		} else {
			logger.error("사용 불가능 토큰!!!");
			throw new UnAuthorizedException();
		}
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}

	@GetMapping("/list") // 질문글과 그에 해당하는 답변글 목록을 리턴
	public ResponseEntity<ResponseDto> listQuestion() throws Exception {
		logger.info("QnA 글 목록");
		ResponseDto responseDto = new ResponseDto();
		ArrayList<QuestionDto> qList = (ArrayList<QuestionDto>) qnaService.listQuestion(); // 질문 글 불러옴
		int qSize = qList.size();
		ArrayList<Map<String, Object>> list = new ArrayList<>();
		for(int i = 0; i < qSize; i++) { 
			Map<String, Object> hm = new HashMap<>();
			// 질문 글 담기
			hm.put("question", qList.get(i)); 
			// 질문 글에 해당하는 답변 글'들' 담기
			hm.put("answers", qnaService.listAnswer(qList.get(i).getArticleNo()));
			list.add(hm);
		}
		responseDto.addPayload("list", list);
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}

	@GetMapping("/question/{articleNo}")
	public ResponseEntity<ResponseDto> getQuestion(@PathVariable("articleNo") int articleNo) throws Exception {
		logger.info("QnA 질문 글 보기 : {}", articleNo);
		ResponseDto responseDto = new ResponseDto();
		qnaService.updateHitQuestion(articleNo);
		responseDto.addPayload("article", qnaService.getQuestion(articleNo));
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}

	@GetMapping("/answer/{articleNo}")
	public ResponseEntity<ResponseDto> getAnswer(@PathVariable("articleNo") int articleNo) throws Exception {
		logger.info("QnA 답변 글 보기 : {}", articleNo);
		ResponseDto responseDto = new ResponseDto();
		qnaService.updateHitAnswer(articleNo);
		responseDto.addPayload("article", qnaService.getAnswer(articleNo));
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/question")
	public ResponseEntity<ResponseDto> modifyQuestion(@RequestBody QuestionDto questionDto, HttpServletRequest req) throws Exception {
		logger.info("QnA 질문 글 수정 : {}", questionDto);
		ResponseDto responseDto = new ResponseDto();

		if(jwtService.checkToken(req.getHeader("access-token"))) {
			String id = (String) jwtService.get("user").get("id");
			QuestionDto targetQDto = qnaService.getQuestion(questionDto.getArticleNo());
			if(id.equals(targetQDto.getMemberId())) {
				qnaService.modifyQuestion(questionDto);
			} else {
				logger.error("글 작성자가 아닙니다.");
				throw new UnAuthorizedException();
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			throw new UnAuthorizedException();
		}
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}

	@PutMapping("/answer")
	public ResponseEntity<ResponseDto> modifyAnswer(@RequestBody AnswerDto answerDto, HttpServletRequest req) throws Exception {
		logger.info("QnA 답변 글 수정 : {}", answerDto);
		ResponseDto responseDto = new ResponseDto();
		
		if(jwtService.checkToken(req.getHeader("access-token"))) {
			String id = (String) jwtService.get("user").get("id");
			AnswerDto targetADto = qnaService.getAnswer(answerDto.getArticleNo());
			if(id.equals(targetADto.getMemberId())) {
				qnaService.modifyAnswer(answerDto);
			} else {
				logger.error("글 작성자가 아닙니다.");
				throw new UnAuthorizedException();
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			throw new UnAuthorizedException();
		}
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}

	@PostMapping("/question/{articleNo}")
	public ResponseEntity<ResponseDto> deleteQuestion(@PathVariable("articleNo") int articleNo, HttpServletRequest req) throws Exception {
		logger.info("QnA 질문 글 삭제 : {}", articleNo);
		ResponseDto responseDto = new ResponseDto();

		if(jwtService.checkToken(req.getHeader("access-token"))) {
			String id = (String) jwtService.get("user").get("id");
			QuestionDto qDto = qnaService.getQuestion(articleNo);
			if(id.equals(qDto.getMemberId())) {
				qnaService.deleteQuestion(articleNo);
			} else {
				logger.error("글 작성자가 아닙니다.");
				throw new UnAuthorizedException();
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			throw new UnAuthorizedException();
		}
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/answer/{articleNo}")
	public ResponseEntity<ResponseDto> deleteAnswer(@PathVariable("articleNo") int articleNo, HttpServletRequest req) throws Exception {
		logger.info("QnA 답변 글 삭제 : {}", articleNo);
		ResponseDto responseDto = new ResponseDto();
		
		if(jwtService.checkToken(req.getHeader("access-token"))) {
			String id = (String) jwtService.get("user").get("id");
			AnswerDto aDto = qnaService.getAnswer(articleNo);
			if(id.equals(aDto.getMemberId())) {
				qnaService.deleteAnswer(articleNo);
			} else {
				logger.error("글 작성자가 아닙니다.");
				throw new UnAuthorizedException();
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
				throw new UnAuthorizedException();
		}
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}

}
