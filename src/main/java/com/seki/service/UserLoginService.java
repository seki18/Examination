package com.seki.service;

import com.seki.bean.UserLogin;

public interface UserLoginService {

	UserLogin findByName(String username);

}
