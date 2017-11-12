package com.seki.bean;

import java.util.Date;
import java.util.List;

public class Student {
    private Integer studentId;

    private String studentName;

    private String sex;

    private Date birthday;
    
    private String birthdayStr;
	
	private String gradeStr;
	
	public String getBirthdayStr() {
		return birthdayStr;
	}

	private Date grade;
	
	private Integer departmentId;
    
    //选课列表
    private List<Mark> markCourseList;
    
    //所属院系名
    private Department department;
    
    public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public String getGradeStr() {
		return gradeStr;
	}

	public void setGradeStr(String gradeStr) {
		this.gradeStr = gradeStr;
	}

    public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

    public List<Mark> getMarkCourseList() {
		return markCourseList;
	}

	public void setMarkCourseList(List<Mark> markCourseList) {
		this.markCourseList = markCourseList;
	}

	public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getGrade() {
        return grade;
    }

    public void setGrade(Date grade) {
        this.grade = grade;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}