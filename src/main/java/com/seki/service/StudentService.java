package com.seki.service;

import java.util.List;

import com.seki.bean.Student;

public interface StudentService {
	
	//根据学号查询学生
	Student findById(Integer studentId);

	//更新学生信息
	void update(Student studentCustom) throws Exception;
		
	//查询所有学生
	List<Student> findAll();

}
