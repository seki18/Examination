package com.seki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seki.bean.Course;
import com.seki.bean.Department;
import com.seki.bean.Message;
import com.seki.service.CourseService;
import com.seki.service.DepartmentService;

@Controller
public class IndexController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@ResponseBody
	@RequestMapping("/courses")
	public Message getCourses(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn,5);
		List<Course> list = courseService.getCourses();
		PageInfo<Course> pageInfo = new PageInfo<Course>(list,5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	@ResponseBody
	@RequestMapping("/courses/{departmentId}")
	public Message getCourses(@RequestParam(value="pn",defaultValue="1")Integer pn, @RequestParam("departmentId")Integer departmentId) {
		PageHelper.startPage(pn,5);
		List<Course> list = courseService.getCoursesByDepartment(departmentId);
		PageInfo<Course> pageInfo = new PageInfo<Course>(list,5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	@ResponseBody
	@RequestMapping("/departments")
	public Message getDepartments() {
		List<Department> list = departmentService.getDpeartments();
		return Message.success().add("departments", list);
	}
	
}
