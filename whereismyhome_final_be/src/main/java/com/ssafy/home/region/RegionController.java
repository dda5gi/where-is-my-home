package com.ssafy.home.region;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.region.dto.RegionDto;
import com.ssafy.home.region.service.RegionService;
import com.ssafy.home.response.ResponseDto;

@RestController
@RequestMapping("/region")
public class RegionController {
	public static final Logger logger = LoggerFactory.getLogger(RegionController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	RegionService regionService;
	
	@GetMapping("/sido")
	public ResponseEntity<ResponseDto> getSido() {
		logger.info("sido코드 호출");
		ResponseDto responseDto = new ResponseDto();
		responseDto.addPayload("dongcode", regionService.getSido());
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<ResponseDto> getGugun(@RequestParam String sido) {
		logger.info("gugun코드 호출");
		ResponseDto responseDto = new ResponseDto();
		RegionDto regionDto = new RegionDto();
		regionDto.setCode(sido);
		responseDto.addPayload("dongcode", regionService.getGugun(regionDto));
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}

	@GetMapping("/dong")
	public ResponseEntity<ResponseDto> getDong(@RequestParam String gugun) {
		logger.info("dong코드 호출");
		ResponseDto responseDto = new ResponseDto();
		RegionDto regionDto = new RegionDto();
		regionDto.setCode(gugun);
		responseDto.addPayload("dongcode", regionService.getDong(regionDto));
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
}
