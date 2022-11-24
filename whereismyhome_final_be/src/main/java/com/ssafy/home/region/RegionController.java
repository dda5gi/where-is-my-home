package com.ssafy.home.region;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.exception.UnAuthorizedException;
import com.ssafy.home.member.service.JwtService;
import com.ssafy.home.region.dto.FavorRegionDto;
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
	@Autowired
	JwtService jwtService;
	
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
	
	@GetMapping("/favor/list")
	public ResponseEntity<ResponseDto> favorRegionList(HttpServletRequest req) {
		logger.info("즐겨찾기 리스트 호출");
		ResponseDto responseDto = new ResponseDto();
		
		if(jwtService.checkToken(req.getHeader("access-token"))) {
			String id = (String) jwtService.get("user").get("id");
			responseDto.addPayload("list", regionService.favorRegionList(id));
		} else {
			logger.error("사용 불가능 토큰!!!");
			throw new UnAuthorizedException();
		}
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/favor")
	public ResponseEntity<ResponseDto> registerFavorRegion(@RequestParam String dongCode, HttpServletRequest req) {
		logger.info("즐겨찾기 등록");
		ResponseDto responseDto = new ResponseDto();
		
		if(jwtService.checkToken(req.getHeader("access-token"))) {
			String id = (String) jwtService.get("user").get("id");
			String regionName = regionService.getRegionName(dongCode).get("regionname");
			FavorRegionDto frDto = new FavorRegionDto();
			frDto.setDongCode(dongCode);
			frDto.setMemberId(id);
			frDto.setRegionName(regionName);
			regionService.registerFavorRegion(frDto);
		} else {
			logger.error("사용 불가능 토큰!!!");
			throw new UnAuthorizedException();
		}
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/favor/delete")
	public ResponseEntity<ResponseDto> deleteFavorRegion(@RequestParam String dongCode, HttpServletRequest req) {
		logger.info("즐겨찾기 삭제");
		ResponseDto responseDto = new ResponseDto();

		if(jwtService.checkToken(req.getHeader("access-token"))) {
			String id = (String) jwtService.get("user").get("id");
			FavorRegionDto frDto = regionService.getFavorRegion(dongCode);
			if(id.equals(frDto.getMemberId())){
				regionService.deleteFavorRegion(frDto);
			} else {
				logger.error("권한 오류");
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
