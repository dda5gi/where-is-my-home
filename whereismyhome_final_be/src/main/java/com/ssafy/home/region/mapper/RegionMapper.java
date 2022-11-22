package com.ssafy.home.region.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.region.dto.RegionDto;

@Mapper
public interface RegionMapper {
	public List<RegionDto> getSido();
	public List<RegionDto> getGugun(RegionDto region);
	public List<RegionDto> getDong(RegionDto region);
}
