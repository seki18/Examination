package com.seki.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seki.bean.Teacher;
import com.seki.mapper.TeacherMapper;
import com.seki.service.DepartmentService;
import com.seki.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Override
	public Teacher findById(Integer teacherId) {
		Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthdayStr = sdf.format(teacher.getBirthday());
		String gradeStr = sdf.format(teacher.getGrade());
		teacher.setBirthdayStr(birthdayStr);
		teacher.setGradeStr(gradeStr);
		teacher.setDepartment(departmentService.findById(teacher.getDepartmentId()));
		return teacher;
	}

	@Override
	public void update(Teacher teacher) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = sdf.parse(teacher.getBirthdayStr());
		Date grade = sdf.parse(teacher.getGradeStr());
		teacher.setBirthday(birthday);
		teacher.setGrade(grade);
		teacherMapper.updateByPrimaryKeySelective(teacher);
	}

	@Override
	public List<Teacher> findAll() {
		List<Teacher> list = teacherMapper.selectByExample(null);
		for (Teacher teacher : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String birthdayStr = sdf.format(teacher.getBirthday());
			String gradeStr = sdf.format(teacher.getGrade());
			teacher.setBirthdayStr(birthdayStr);
			teacher.setGradeStr(gradeStr);
			teacher.setDepartment(departmentService.findById(teacher.getDepartmentId()));
		}
		return list;
	}

}
