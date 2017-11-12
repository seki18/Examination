package com.seki.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seki.bean.Role;
import com.seki.mapper.RoleMapper;
import com.seki.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role findByid(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

}
