package com.ssafy.home.member.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.member.dto.MemberDto;
import com.ssafy.home.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int register(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).register(memberDto);
	}

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getId() == null || memberDto.getPwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String id) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(id);
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		sqlSession.getMapper(MemberMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String id) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getRefreshToken(id);
	}

	@Override
	public void deleRefreshToken(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", null);
		sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}

	@Override
	public int deleteMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(memberDto);
	}
	@Override
	public int modifyMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).modifyMember(memberDto);
	}
}
