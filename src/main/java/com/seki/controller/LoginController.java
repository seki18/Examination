package com.seki.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seki.bean.Message;

@Controller
public class LoginController {

	@ResponseBody
	@RequestMapping(value="/login",method= {RequestMethod.POST})
	public Message login(@RequestParam("username")String username, @RequestParam("password")String password) throws Exception{
		//Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        subject.login(token);
        if (subject.hasRole("admin")) {
            return new Message(210,"����Ա");
        } else if (subject.hasRole("teacher")) {
        	return new Message(220,"��ʦ");
        } else if (subject.hasRole("student")) {
        	return new Message(230,"ѧ��");
        }

        return Message.success();
	}
	
}
