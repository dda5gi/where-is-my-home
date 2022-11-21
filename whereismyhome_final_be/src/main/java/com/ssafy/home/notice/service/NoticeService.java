package com.ssafy.home.notice.service;

import java.util.List;

import com.ssafy.home.notice.dto.NoticeDto;

public interface NoticeService {
	public int writeNotice(NoticeDto noticeDto) throws Exception;
	public List<NoticeDto> listNotice() throws Exception;
	public NoticeDto getNotice(int articleNo) throws Exception;
	public int modifyNotice(NoticeDto noticeDto) throws Exception;
	public int deleteNotice(int articleNo) throws Exception;
	public int updateHit(int articleNo) throws Exception;
}
