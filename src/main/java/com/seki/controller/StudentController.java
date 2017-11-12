package com.seki.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seki.bean.Course;
import com.seki.bean.Mark;
import com.seki.bean.Message;
import com.seki.bean.Student;
import com.seki.exception.CustomException;
import com.seki.service.CourseService;
import com.seki.service.MarkService;
import com.seki.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private MarkService markService;
	
	@RequestMapping("/all_course")
	public String toAllCourse() {
		return "student/all_course";
	}
	
	@RequestMapping("/choose_course")
	public String toChooseCourse() {
		return "student/choose_course";
	}
	
	@RequestMapping("/over_course")
	public String toOverCourse() {
		return "student/over_course";
	}
	
	//获取当前用户名
	public Integer getStudentId() {
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Integer studentId = Integer.parseInt(username);
		return studentId;
	}
	
	//该系的全部课程
	@ResponseBody
	@RequestMapping("/courses")
	public Message getCourses(@RequestParam(value="pn",defaultValue="1")Integer pn) {
        Integer departmentId = studentService.findById(getStudentId()).getDepartmentId();
        
		PageHelper.startPage(pn,5);
		List<Course> list = courseService.getCoursesByDepartment(departmentId);
		PageInfo<Course> pageInfo = new PageInfo<Course>(list,5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	
	//选课操作
	@ResponseBody
	@RequestMapping(value="/chooseCourse",method= {RequestMethod.POST})
	public Message chooseCourse(@RequestParam("courseId")Integer id) throws Exception {
        Mark mark = new Mark();
        mark.setCourseId(id);
        mark.setStudentId(getStudentId());
        
        if (markService.findMark(mark) == null) {
            markService.save(mark);
        } else {
            throw new CustomException("�ÿγ��Ѿ�ѡ��");
        }

		return Message.success();
	}
	
	//已选课程
	@ResponseBody
	@RequestMapping("/selectedCourse")
	public Message selectedCourse(@RequestParam(value="pn",defaultValue="1")Integer pn) throws Exception {
        PageHelper.startPage(pn,5);
        List<Course> list = markService.findSelectedCourses(getStudentId());
        PageInfo<Course> pageInfo = new PageInfo<Course>(list,5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	//退课操作
	@ResponseBody
	@RequestMapping(value="/backCourse/{id}",method= {RequestMethod.DELETE})
	public Message backCourse(@PathVariable("id")Integer id) {
		Mark mark = new Mark();
		mark.setCourseId(id);
		mark.setStudentId(getStudentId());
		markService.delete(mark);
		
		return Message.success();
	}
	
	//已修课程
	@ResponseBody
	@RequestMapping("/overCourse")
	public Message overCourse(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn,5);
		List<Mark> list = markService.findOverCourses(getStudentId());
		PageInfo<Mark> pageInfo = new PageInfo<Mark>(list,5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	//查询个人信息
	@ResponseBody
	@RequestMapping(value="/info",method= {RequestMethod.GET})
	public Message info() {
		Student student = studentService.findById(getStudentId());
		
		return Message.success().add("student", student);
	}
	
	//更新个人信息
	@ResponseBody
	@RequestMapping(value="/update",method= {RequestMethod.PUT})
	public Message update(Student student) {
		student.setStudentId(getStudentId());
		try {
			studentService.update(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}
	
}
