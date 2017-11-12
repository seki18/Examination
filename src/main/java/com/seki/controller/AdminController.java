package com.seki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seki.bean.Message;
import com.seki.bean.Student;
import com.seki.bean.Teacher;
import com.seki.service.StudentService;
import com.seki.service.TeacherService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;

	@RequestMapping("/course_admin")
	public String toCourseAdmin() {
		return "admin/course_admin";
	}
	
	@RequestMapping("/student_admin")
	public String toStudentAdmin() {
		return "admin/student_admin";
	}
	
	@RequestMapping("/teacher_admin")
	public String toTeacherAdmin() {
		return "admin/teacher_admin";
	}
	
	//学生列表
	@ResponseBody
	@RequestMapping("/students")
	public Message getStudents(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn,5);
		List<Student> list = studentService.findAll();
		PageInfo<Student> pageInfo = new PageInfo<Student>(list,5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	//查询学生信息
	@ResponseBody
	@RequestMapping(value="/studentInfo",method= {RequestMethod.GET})
	public Message studentInfo(@RequestParam("studentId")Integer studentId) {
		Student student = studentService.findById(studentId);
		
		return Message.success().add("student", student);
	}
		
	//更新学生信息
	@ResponseBody
	@RequestMapping(value="/studentUpdate",method= {RequestMethod.PUT})
	public Message studentUpdate(Student student,@RequestParam("studentId")Integer studentId) {
		student.setStudentId(studentId);
		try {
			studentService.update(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}
	
	//教师列表
	@ResponseBody
	@RequestMapping("/teachers")
	public Message getTeachers(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn,5);
		List<Teacher> list = teacherService.findAll();
		PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list,5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	//查询教师信息
	@ResponseBody
	@RequestMapping(value="/teacherInfo",method= {RequestMethod.GET})
	public Message info(@RequestParam("teacherId")Integer teacherId) {
		Teacher teacher = teacherService.findById(teacherId);
			
		return Message.success().add("teacher", teacher);
	}
		
	//更新教师信息
	@ResponseBody
	@RequestMapping(value="/teacherUpdate",method= {RequestMethod.PUT})
	public Message update(Teacher teacher,@RequestParam("teacherId")Integer teacherId) {
		teacher.setTeacherId(teacherId);
		System.out.println(teacher.getTeacherName()+","+teacher.getBirthdayStr()+","+teacher.getBirthday());
		try {
			teacherService.update(teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}
	
	
}
