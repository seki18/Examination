package com.seki.service;

import java.util.List;

import com.seki.bean.Course;

public interface CourseService {
	
	//根据id查询课程
	Course findById(Integer courseId);
	
	//查询所有课程
	List<Course> getCourses();

	//根据院系查询课程
	List<Course> getCoursesByDepartment(Integer departmentId);
	
	//根据教师查询课程
	List<Course> getCoursesByTeacherId(Integer teacherId);

}
