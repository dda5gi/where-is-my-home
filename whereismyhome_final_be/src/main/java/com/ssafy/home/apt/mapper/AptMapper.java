package com.ssafy.home.apt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.apt.dto.AptDto;

@Mapper
public interface AptMapper {
	List<AptDto> getAptList(String dongCode);
}
