package com.ssafy.home.apt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.apt.dto.DealDto;
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

	@GetMapping("/deal")
	public ResponseEntity<ResponseDto> getAptDeal(@RequestParam String aptCode) throws Exception{
		logger.info("아파트 면적 별 거래 기록 호출");
		ResponseDto responseDto = new ResponseDto();
		// 아파트 거래기록 호출
		ArrayList<DealDto> list = (ArrayList<DealDto>) aptService.getDealList(aptCode);

		// "면적" : [ {날짜, 가격}, { }, { } .. ] 형태로 변환하기
		Map<String, ArrayList<HashMap<String, Object>>> hm = new HashMap<>();
		for(DealDto deal : list) {
			if(hm.get(deal.getArea())== null) {
				hm.put(deal.getArea(), new ArrayList<HashMap<String, Object>>());
			}
			HashMap<String, Object> tm = new HashMap<>();
			tm.put("dealAmount", deal.getDealAmount());
			String year = deal.getDealYear();
			String month = deal.getDealMonth();
			month = month.length()==1 ? "0"+month : month;
			String day = deal.getDealDay();
			day = day.length()==1 ? "0"+day: day;
			tm.put("date", year + "-" + month + "-" + day);
			hm.get(deal.getArea()).add(tm);
		}
		ArrayList<String> areas = new ArrayList<String>();
		for(String key : hm.keySet()) {
			areas.add(key);
		}
		// 면적 순서대로 리스트화
//		List<Entry<String, ArrayList<HashMap<String, Object>>>> result = new ArrayList<Entry<String, ArrayList<HashMap<String, Object>>>>(hm.entrySet());
//		// 면적 순서대로 정렬하기
//		Collections.sort(result, new Comparator<Entry<String, ArrayList<HashMap<String, Object>>>>() {
//			public int compare(Entry<String, ArrayList<HashMap<String, Object>>> obj1, Entry<String, ArrayList<HashMap<String, Object>>> obj2) {
//				float a = Float.parseFloat(obj1.getKey());
//				float b = Float.parseFloat(obj2.getKey());
//				if(a < b) return -1;
//				else return 1;
//			}
//		});
		
//		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
//		for(String key : hm.keySet()) {
//			HashMap<String, Object> tmp = new HashMap<String, Object>();
//			tmp.put("area", key);
//			tmp.put("deals", hm.get(key));
//			result.add(tmp);
//		}
		
		responseDto.addPayload("areas", areas);
		responseDto.addPayload("list", hm);
		responseDto.setMsg(SUCCESS);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
	}
}
