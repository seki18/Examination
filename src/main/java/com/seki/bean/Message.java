package com.seki.bean;

import java.util.HashMap;
import java.util.Map;

public class Message{

	private int code;

	private String msg;

	private Map<String,Object> map = new HashMap<String,Object>();

	public Message() {
		super();
	}

	public Message(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public static Message success() {
		Message result = new Message();
		result.setCode(200);
		result.setMsg("处理成功");
		return result;
	}
	
	public static Message fail() {
		Message result = new Message();
		result.setCode(400);
		result.setMsg("处理失败");
		return result;
	}
	
	public Message add(String key, Object value) {
		this.getMap().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
