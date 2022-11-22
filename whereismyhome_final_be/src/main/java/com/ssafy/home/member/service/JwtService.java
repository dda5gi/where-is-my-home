package com.ssafy.home.member.service;

import java.util.Map;

public interface JwtService {

	<T> String createAccessToken(Map<String, Object> map);
	<T> String createRefreshToken(Map<String, Object> map);
	<T> String create(Map<String, Object> map, String subject, long expire);
	Map<String, Object> get(String key);
	String getUserId();
	boolean checkToken(String jwt);
	
}
