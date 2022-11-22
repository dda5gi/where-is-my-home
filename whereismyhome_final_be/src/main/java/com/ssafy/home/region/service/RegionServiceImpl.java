package com.ssafy.home.region.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.region.dto.Region;
import com.ssafy.home.region.mapper.RegionMapper;
import com.ssafy.home.region.mapper.regionMapper;

@Service
public class RegionServiceImpl implements RegionService {

	private final RegionMapper regionMapper;

	public RegionServiceImpl(RegionMapper regionMapper) {
		this.regionMapper = regionMapper;
	}
			
	@Override
	public List<Region> getSido() {
		return regionMapper.getSido();
	}

	@Override
	public List<Region> getGugun(Region region) {
		return regionMapper.getGugun(region);
	}

	@Override
	public List<Region> getDong(Region region) {
		return regionMapper.getDong(region);
	}

}
