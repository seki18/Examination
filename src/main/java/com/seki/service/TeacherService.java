package com.seki.service;

import java.util.List;

import com.seki.bean.Teacher;

public interface TeacherService {

	//根据教师id查询教师
	Teacher findById(Integer teacherId);

	//更新个人信息
	void update(Teacher teacher) throws Exception ;

	//查询全部
	List<Teacher> findAll();
	
}
