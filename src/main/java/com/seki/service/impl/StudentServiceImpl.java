package com.seki.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seki.bean.Student;
import com.seki.mapper.StudentMapper;
import com.seki.service.DepartmentService;
import com.seki.service.MarkService;
import com.seki.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private MarkService markService;
	
	@Autowired
	private DepartmentService departmentService;

	@Override
	public Student findById(Integer studentId) {
		Student student = studentMapper.selectByPrimaryKey(studentId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthdayStr = sdf.format(student.getBirthday());
		String gradeStr = sdf.format(student.getGrade());
		student.setBirthdayStr(birthdayStr);
		student.setGradeStr(gradeStr);
		return student;
	}

	@Override
	public void update(Student student) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = sdf.parse(student.getBirthdayStr());
		Date grade = sdf.parse(student.getGradeStr());
		student.setBirthday(birthday);
		student.setGrade(grade);
		if(student.getDepartmentId()!=null && findById(student.getStudentId()).getDepartmentId()!=student.getDepartmentId()) {
			markService.deleteByStudentId(student.getStudentId());
		}
		studentMapper.updateByPrimaryKeySelective(student);
	}

	@Override
	public List<Student> findAll() {
		List<Student> list = studentMapper.selectByExample(null);
		for (Student student : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String birthdayStr = sdf.format(student.getBirthday());
			String gradeStr = sdf.format(student.getGrade());
			student.setBirthdayStr(birthdayStr);
			student.setGradeStr(gradeStr);
			student.setDepartment(departmentService.findById(student.getDepartmentId()));
		}
		return list;
	}

}
