package com.seki.service;

import java.util.List;

import com.seki.bean.Course;
import com.seki.bean.Mark;

public interface MarkService {
	
	//查询学生成绩
		Mark findMark(Mark mark) throws Exception;
		
		//选课
		void save(Mark mark) throws Exception;
		
		//查询已选课程
		List<Course> findSelectedCourses(Integer studentId) throws Exception;

		//退课
		void delete(Mark mark);

		//清空已选课程
		void deleteByStudentId(Integer studentId);
		
		//根据课程查询学生
		List<Mark> findStudentByCourse(Integer courseId);
		
		//打分
		void mark(Mark mark);

		//查询已修课程
		List<Mark> findOverCourses(Integer studentId);
	}
