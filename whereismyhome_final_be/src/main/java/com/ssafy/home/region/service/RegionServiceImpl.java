package com.ssafy.home.region.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.region.dto.FavorRegionDto;
import com.ssafy.home.region.dto.RegionDto;
import com.ssafy.home.region.mapper.RegionMapper;

@Service
public class RegionServiceImpl implements RegionService {

	private final RegionMapper regionMapper;

	public RegionServiceImpl(RegionMapper regionMapper) {
		this.regionMapper = regionMapper;
	}
			
	@Override
	public List<RegionDto> getSido() {
		return regionMapper.getSido();
	}

	@Override
	public List<RegionDto> getGugun(RegionDto region) {
		return regionMapper.getGugun(region);
	}

	@Override
	public List<RegionDto> getDong(RegionDto region) {
		return regionMapper.getDong(region);
	}
	@Override
	public List<FavorRegionDto> favorRegionList(String id) {
		return regionMapper.favorRegionList(id);
	}
	@Override
	public Map<String, String> getRegionName(String dongCode) {
		return regionMapper.getRegionName(dongCode);
	}
	@Override
	public int registerFavorRegion(FavorRegionDto frDto) {
		return regionMapper.registerFavorRegion(frDto);
	}
	@Override
	public FavorRegionDto getFavorRegion(String dongCode) {
		return regionMapper.getFavorRegion(dongCode);
	}
	@Override
	public int deleteFavorRegion(FavorRegionDto frDto) {
		return regionMapper.deleteFavorRegion(frDto);
	}
}
