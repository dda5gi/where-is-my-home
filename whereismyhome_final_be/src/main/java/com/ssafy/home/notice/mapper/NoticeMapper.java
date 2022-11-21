package com.ssafy.home.notice.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.notice.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	public int writeNotice(NoticeDto noticeDto) throws SQLException;
	public List<NoticeDto> listNotice() throws SQLException;
	public NoticeDto getNotice(int articleNo) throws SQLException;
	public int modifyNotice(NoticeDto noticeDto) throws SQLException;
	public int deleteNotice(int articleNo) throws SQLException;
	public int updateHit(int articleNo) throws SQLException;
}
