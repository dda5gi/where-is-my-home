package com.ssafy.home.region.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.region.dto.Region;

@Mapper
public interface RegionMapper {
	public List<Region> getSido();
	public List<Region> getGugun(Region region);
	public List<Region> getDong(Region region);
}
