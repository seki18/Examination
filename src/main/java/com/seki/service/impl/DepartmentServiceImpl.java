package com.seki.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seki.bean.Department;
import com.seki.mapper.DepartmentMapper;
import com.seki.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public List<Department> getDpeartments() {
		List<Department> list = new ArrayList<>();
		list = departmentMapper.selectByExample(null);
		return list;
	}

	@Override
	public Department findById(Integer departmentId) {
		Department department = departmentMapper.selectByPrimaryKey(departmentId);
		return department;
	}

}
