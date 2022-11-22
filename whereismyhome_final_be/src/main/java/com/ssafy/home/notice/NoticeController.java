package com.ssafy.home.notice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.notice.dto.NoticeDto;
import com.ssafy.home.notice.service.NoticeService;
import com.ssafy.home.response.ResponseDto;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	public static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/list")
	public ResponseEntity<ResponseDto> getList() throws Exception{
		logger.info("공지사항 리스트 호출");
		ResponseDto responseDto = new ResponseDto();
		responseDto.addPayload("list", noticeService.listNotice());
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{articleNo}")
	public ResponseEntity<ResponseDto> getNotice(@PathVariable("articleNo") int articleNo) throws Exception {
		logger.info("공지사항 글 보기 : {}", articleNo);
		ResponseDto responseDto = new ResponseDto();
		noticeService.updateHit(articleNo);
		responseDto.addPayload("article", noticeService.getNotice(articleNo));
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDto> writeNotice(@RequestBody NoticeDto noticeDto) throws Exception {
		logger.info("공지사항 글 쓰기 {}", noticeDto);
		ResponseDto responseDto = new ResponseDto();
		int result = noticeService.writeNotice(noticeDto);
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
	@PutMapping
	public ResponseEntity<ResponseDto> modifyNotice(@RequestBody NoticeDto noticeDto) throws Exception {
		logger.info("공지사항 글  수정 {}", noticeDto);
		ResponseDto responseDto = new ResponseDto();
		int result = noticeService.modifyNotice(noticeDto);
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{articleNo}")
	public ResponseEntity<ResponseDto> deleteNotice(@PathVariable("articleNo") int articleNo) throws Exception {
		logger.info("공지사항 글  삭제: {}", articleNo);
		ResponseDto responseDto = new ResponseDto();
		int result = noticeService.deleteNotice(articleNo);
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
}
