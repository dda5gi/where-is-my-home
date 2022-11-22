package com.ssafy.home.apt;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	//&regCode=1111011500
	@GetMapping("/list/{dongcode}")
	public ResponseEntity<ResponseDto> getList(@PathVariable("dongcode") String dongCode) throws Exception{
		logger.info("아파트 리스트 호출");
		ResponseDto responseDto = new ResponseDto();
		responseDto.addPayload("list", aptService.getAptList(dongCode));
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
}
