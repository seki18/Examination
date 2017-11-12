package com.seki.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seki.bean.Course;
import com.seki.bean.Mark;
import com.seki.bean.MarkExample;
import com.seki.bean.Student;
import com.seki.mapper.MarkMapper;
import com.seki.service.CourseService;
import com.seki.service.MarkService;
import com.seki.service.StudentService;

@Service
public class MarkServiceImpl implements MarkService {
	
	@Autowired
	private MarkMapper markMapper;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;

	@Override
	public Mark findMark(Mark mark) throws Exception {
		MarkExample markExample = new MarkExample();
		MarkExample.Criteria criteria = markExample.createCriteria();
		criteria.andCourseIdEqualTo(mark.getCourseId());
		criteria.andStudentIdEqualTo(mark.getStudentId());
		List<Mark> list = markMapper.selectByExample(markExample);
		
		if(list.size()>0) {
			Mark m = list.get(0);
			Student student = studentService.findById(mark.getStudentId());;
			m.setStudent(student);
			return m;
		}
		return null;
	}

	@Override
	public void save(Mark mark) throws Exception {
		markMapper.insert(mark);
	}

	@Override
	public List<Course> findSelectedCourses(Integer studentId) throws Exception {
		MarkExample markExample = new MarkExample();
		MarkExample.Criteria criteria = markExample.createCriteria();
		criteria.andStudentIdEqualTo(studentId);
		List<Mark> marks = markMapper.selectByExample(markExample);
		List<Course> list = new ArrayList<>();
		for (Mark mark : marks) {
			mark.setCourse(courseService.findById(mark.getCourseId()));
			list.add(mark.getCourse());
		}
		
		return list;
	}

	@Override
	public void delete(Mark mark) {
		MarkExample markExample = new MarkExample();
		MarkExample.Criteria criteria = markExample.createCriteria();
		criteria.andCourseIdEqualTo(mark.getCourseId());
		criteria.andStudentIdEqualTo(mark.getStudentId());
		markMapper.deleteByExample(markExample);
	}

	@Override
	public void deleteByStudentId(Integer studentId) {
		MarkExample markExample = new MarkExample();
		MarkExample.Criteria criteria = markExample.createCriteria();
		criteria.andStudentIdEqualTo(studentId);
		markMapper.deleteByExample(markExample);
	}

	@Override
	public List<Mark> findStudentByCourse(Integer courseId) {
		MarkExample markExample = new MarkExample();
		MarkExample.Criteria criteria = markExample.createCriteria();
		criteria.andCourseIdEqualTo(courseId);
		List<Mark> list = markMapper.selectByExample(markExample);
		for (Mark mark : list) {
			mark.setStudent(studentService.findById(mark.getStudentId()));
		}
		return list;
	}

	@Override
	public void mark(Mark mark) {
		MarkExample markExample = new MarkExample();
		MarkExample.Criteria criteria = markExample.createCriteria();
		criteria.andCourseIdEqualTo(mark.getCourseId());
		criteria.andStudentIdEqualTo(mark.getStudentId());
		markMapper.updateByExample(mark, markExample);
	}

	@Override
	public List<Mark> findOverCourses(Integer studentId) {
		MarkExample markExample = new MarkExample();
		MarkExample.Criteria criteria = markExample.createCriteria();
		criteria.andStudentIdEqualTo(studentId);
		criteria.andMarkIsNotNull();
		List<Mark> list = markMapper.selectByExample(markExample);
		for (Mark mark : list) {
			mark.setCourse(courseService.findById(mark.getCourseId()));
		}
		
		return list;
	}

}
