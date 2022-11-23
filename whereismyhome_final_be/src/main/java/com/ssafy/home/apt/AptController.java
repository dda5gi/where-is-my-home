package com.ssafy.home.apt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.apt.service.AptService;
import com.ssafy.home.response.ResponseDto;

@RestController
@RequestMapping("/apt")
public class AptController {
	
	public static final Logger logger = LoggerFactory.getLogger(AptController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	AptService aptService;

	//&dongCode=1111011500
	@GetMapping("/list")
	public ResponseEntity<ResponseDto> getAptList(@RequestParam String dongCode) throws Exception{
		logger.info("아파트 리스트 호출");
		ResponseDto responseDto = new ResponseDto();
		responseDto.addPayload("list", aptService.getAptList(dongCode));
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
//	@GetMapping("/deal")
//	public ResponseEntity<ResponseDto> getAptDeal(@RequestParam String aptCode) throws Exception{
//		logger.info("아파트 면적 별 거래 기록 호출");
//		ResponseDto responseDto = new ResponseDto();
//		responseDto.addPayload("list", aptService.getAptDeal(aptCode));
//		responseDto.setMsg(SUCCESS);
//		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
//	}
}
