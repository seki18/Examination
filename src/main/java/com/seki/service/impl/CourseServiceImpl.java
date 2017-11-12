package com.seki.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seki.bean.Course;
import com.seki.bean.CourseExample;
import com.seki.bean.CourseExample.Criteria;
import com.seki.mapper.CourseMapper;
import com.seki.mapper.DepartmentMapper;
import com.seki.mapper.TeacherMapper;
import com.seki.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public List<Course> getCourses() {
		List<Course> list = new ArrayList<>();
		list = courseMapper.selectByExample(null);
		for (Course course : list) {
			course.setTeacher(teacherMapper.selectByPrimaryKey(course.getTeacherId()));
			course.setDepartment(departmentMapper.selectByPrimaryKey(course.getDepartmentId()));
		}
		return list;
	}

	@Override
	public List<Course> getCoursesByDepartment(Integer departmentId) {
		List<Course> list = new ArrayList<>();
		CourseExample courseExample = new CourseExample();
		Criteria createCriteria = courseExample.createCriteria();
		createCriteria.andDepartmentIdEqualTo(departmentId);
		list = courseMapper.selectByExample(courseExample);
		for (Course course : list) {
			course.setTeacher(teacherMapper.selectByPrimaryKey(course.getTeacherId()));
			course.setDepartment(departmentMapper.selectByPrimaryKey(course.getDepartmentId()));
		}
		return list;
	}

	@Override
	public Course findById(Integer courseId) {
		Course course = courseMapper.selectByPrimaryKey(courseId);
		course.setTeacher(teacherMapper.selectByPrimaryKey(course.getTeacherId()));
		course.setDepartment(departmentMapper.selectByPrimaryKey(course.getDepartmentId()));
		return course;
	}

	@Override
	public List<Course> getCoursesByTeacherId(Integer teacherId) {
		List<Course> list = new ArrayList<>();
		CourseExample courseExample = new CourseExample();
		Criteria createCriteria = courseExample.createCriteria();
		createCriteria.andTeacherIdEqualTo(teacherId);
		list = courseMapper.selectByExample(courseExample);
		for (Course course : list) {
			course.setTeacher(teacherMapper.selectByPrimaryKey(course.getTeacherId()));
			course.setDepartment(departmentMapper.selectByPrimaryKey(course.getDepartmentId()));
		}
		return list;
	}

}
