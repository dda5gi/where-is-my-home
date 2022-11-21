package com.ssafy.home.response;

import java.util.HashMap;
import java.util.Map;

public class ResponseDto {
	Map<String, Object> payload;
	String msg;

	public ResponseDto() {
		payload = new HashMap<>();
		msg = "def";
	}

	public Map<String, Object> getPayload() {
		return payload;
	}

	public void addPayload(String key, Object data) {
		payload.put(key, data);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
