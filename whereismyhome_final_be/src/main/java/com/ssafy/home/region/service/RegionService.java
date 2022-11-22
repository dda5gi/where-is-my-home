package com.ssafy.home.region.service;

import java.util.List;

import com.ssafy.home.region.dto.Region;

public interface RegionService {
	List<Region> getSido();
	List<Region> getGugun(Region region);
	List<Region> getDong(Region region);
}
