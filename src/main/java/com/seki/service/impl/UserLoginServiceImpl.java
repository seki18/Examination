package com.seki.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seki.bean.UserLogin;
import com.seki.bean.UserLoginExample;
import com.seki.bean.UserLoginExample.Criteria;
import com.seki.mapper.UserLoginMapper;
import com.seki.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	@Autowired
	private UserLoginMapper userLoginMapper;

	@Override
	public UserLogin findByName(String username) {
        UserLoginExample userLoginExample = new UserLoginExample();
        Criteria createCriteria = userLoginExample.createCriteria();
        createCriteria.andUsernameEqualTo(username);

        List<UserLogin> list = userLoginMapper.selectByExample(userLoginExample);
        
        if(list.size()!=0) {
        	return list.get(0);
        }else {
        	return null;
        }
	}

}
