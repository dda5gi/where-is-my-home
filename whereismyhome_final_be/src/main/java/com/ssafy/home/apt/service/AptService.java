package com.ssafy.home.apt.service;

import java.util.List;

import com.ssafy.home.apt.dto.AptDto;
import com.ssafy.home.apt.dto.DealDto;

public interface AptService {
	List<AptDto> getAptList(String dongCode);
	List<DealDto> getDealList(String aptCode);
}
