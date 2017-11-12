package com.seki.service;

import java.util.List;

import com.seki.bean.Department;

public interface DepartmentService {
	
	List<Department> getDpeartments();
	
	Department findById(Integer departmentId);
	
}
