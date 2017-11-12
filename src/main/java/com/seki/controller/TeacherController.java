package com.seki.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seki.bean.Course;
import com.seki.bean.Mark;
import com.seki.bean.Message;
import com.seki.bean.Teacher;
import com.seki.service.CourseService;
import com.seki.service.MarkService;
import com.seki.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	private Integer courseId;	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private MarkService markService;

	@RequestMapping("/my_course")
	public String myCourse() {
		return "teacher/my_course";
	}
	
	//获取当前用户名
	public Integer getTeacherId() {
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Integer teacherId = Integer.parseInt(username);
		return teacherId;
	}
	
	//查询我的课程
	@ResponseBody
	@RequestMapping("/courses")
	public Message myCourse(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn,5);
		List<Course> list = courseService.getCoursesByTeacherId(getTeacherId());
		PageInfo<Course> pageInfo = new PageInfo<Course>(list,5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	//转到成绩页面
	@RequestMapping("/toStudents")
	public String getStudents(@RequestParam("courseId")Integer courseId) {
		this.courseId = courseId;
		return "teacher/students_mark";
	}
	
	//查询选课学生
	@ResponseBody
	@RequestMapping("/students")
	public Message getStudentsInfo(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn,5);
		List<Mark> list = markService.findStudentByCourse(courseId);
		PageInfo<Mark> pageInfo = new PageInfo<Mark>(list,5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	//打分操作
	@ResponseBody
	@RequestMapping(value="/mark",method= {RequestMethod.POST})
	public Message mark(@RequestParam("studentId")Integer studentId,@RequestParam("mark")Integer mark) {
		Mark m = new Mark();
		m.setCourseId(courseId);
		m.setStudentId(studentId);
		m.setMark(mark);
		markService.mark(m);
		return Message.success();
	}
	
	//查询个人信息
	@ResponseBody
	@RequestMapping(value="/info",method= {RequestMethod.GET})
	public Message info() {
		Teacher teacher = teacherService.findById(getTeacherId());
			
		return Message.success().add("teacher", teacher);
	}
	
	//更新个人信息
	@ResponseBody
	@RequestMapping(value="/update",method= {RequestMethod.PUT})
	public Message update(Teacher teacher) {
		teacher.setTeacherId(getTeacherId());
		try {
			teacherService.update(teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}
	
}