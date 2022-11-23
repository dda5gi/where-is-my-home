package com.ssafy.home.apt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.apt.dto.AptDto;
import com.ssafy.home.apt.dto.DealDto;

@Mapper
public interface AptMapper {
	List<AptDto> getAptList(String dongCode);
	List<DealDto> getDealList(String aptCode);
}
