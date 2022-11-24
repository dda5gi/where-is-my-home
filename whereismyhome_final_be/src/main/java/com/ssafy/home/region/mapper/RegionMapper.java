package com.ssafy.home.region.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.region.dto.FavorRegionDto;
import com.ssafy.home.region.dto.RegionDto;

@Mapper
public interface RegionMapper {
	public List<RegionDto> getSido();
	public List<RegionDto> getGugun(RegionDto region);
	public List<RegionDto> getDong(RegionDto region);

	public List<FavorRegionDto> favorRegionList(String id);
	public Map<String, String> getRegionName(String dongCode);
	public int registerFavorRegion(FavorRegionDto frDto);
	public FavorRegionDto getFavorRegion(String dongCode);
	public int deleteFavorRegion(FavorRegionDto frDto);
}
