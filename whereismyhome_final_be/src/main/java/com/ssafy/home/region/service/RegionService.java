package com.ssafy.home.region.service;

import java.util.List;
import java.util.Map;

import com.ssafy.home.region.dto.FavorRegionDto;
import com.ssafy.home.region.dto.RegionDto;

public interface RegionService {
	List<RegionDto> getSido();
	List<RegionDto> getGugun(RegionDto region);
	List<RegionDto> getDong(RegionDto region);

	List<FavorRegionDto> favorRegionList(String id);
	public Map<String, String> getRegionName(String dongCode);
	public int registerFavorRegion(FavorRegionDto frDto);
	public FavorRegionDto getFavorRegion(String dongCode);
	public int deleteFavorRegion(FavorRegionDto frDto);
}
