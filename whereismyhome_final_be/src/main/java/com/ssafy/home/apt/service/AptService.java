package com.ssafy.home.apt.service;

import java.util.List;

import com.ssafy.home.apt.dto.AptDto;

public interface AptService {
	List<AptDto> getAptList(String dongCode);
}
