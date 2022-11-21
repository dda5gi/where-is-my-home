package com.ssafy.home.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.notice.dto.NoticeDto;
import com.ssafy.home.notice.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{

	private NoticeMapper noticeMapper;

	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}
	@Override
	public int writeNotice(NoticeDto noticeDto) throws Exception {
		return noticeMapper.writeNotice(noticeDto);
	}

	@Override
	public List<NoticeDto> listNotice() throws Exception {
		return noticeMapper.listNotice();
	}

	@Override
	public NoticeDto getNotice(int articleNo) throws Exception {
		return noticeMapper.getNotice(articleNo);
	}

	@Override
	public int modifyNotice(NoticeDto noticeDto) throws Exception {
		return noticeMapper.modifyNotice(noticeDto);
	}

	@Override
	public int deleteNotice(int articleNo) throws Exception {
		return noticeMapper.deleteNotice(articleNo);
	}

	@Override
	public int updateHit(int articleNo) throws Exception {
		return noticeMapper.updateHit(articleNo);
	}
}
