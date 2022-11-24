package com.ssafy.home.apt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.home.apt.dto.AptDto;
import com.ssafy.home.apt.dto.DealDto;
import com.ssafy.home.apt.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {

	private final AptMapper aptMapper;
	
	public AptServiceImpl(AptMapper aptMapper) {
		this.aptMapper = aptMapper;
	}
	
	@Override
	public List<AptDto> getAptList(String dongCode) {
		return aptMapper.getAptList(dongCode);
	}

	@Override
	public List<DealDto> getDealList(String aptCode) {
		return aptMapper.getDealList(aptCode);
	}
}
