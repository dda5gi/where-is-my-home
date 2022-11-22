package com.ssafy.home.region.service;

import java.util.List;

import com.ssafy.home.region.dto.RegionDto;

public interface RegionService {
	List<RegionDto> getSido();
	List<RegionDto> getGugun(RegionDto region);
	List<RegionDto> getDong(RegionDto region);
}
