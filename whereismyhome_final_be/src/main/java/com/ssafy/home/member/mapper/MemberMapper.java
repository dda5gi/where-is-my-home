package com.ssafy.home.member.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public int register(MemberDto memberDto) throws SQLException;
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String id) throws SQLException;
	public int deleteMember(MemberDto memberDto) throws SQLException;
	public int modifyMember(MemberDto memberDto) throws SQLException;
	
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String id) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
