package com.seki.controller;


import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seki.bean.Message;
import com.seki.exception.CustomException;

@ControllerAdvice
public class ExceptionController {

	@ResponseBody
	@ExceptionHandler(CustomException.class)
	public Message customException() {
		return Message.fail();
	}
	
	@ResponseBody
	@ExceptionHandler(ShiroException.class)
	public Message loginException() {
		return Message.fail();
	}
	
}
