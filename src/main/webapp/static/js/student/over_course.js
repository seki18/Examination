$(function() {
	to_page_over(1);
});

function to_page_over(pn) {
	$.ajax({
		url : "../student/overCourse",
		data : "pn=" + pn,
		type : "GET",
		success : function(result) {
			// 1.显示课程数据
			build_courses_table(result);
			// 2.显示分页条
			build_page_nav(result);
		}
	});
}

function build_courses_table(result) {
	$("#courses_table tbody").empty();
	var courses = result.map.pageInfo.list;
	$.each(courses, function(index, item) {
		var courseIdTd = $("<td></td>").append(item.courseId);
		var courseNameTd = $("<td></td>").append(item.course.courseName);
		var teacherIdTd = $("<td></td>").append(item.course.teacher.teacherName);
		var departmentNameTd = $("<td></td>").append(
				item.course.department.departmentName);
		var courseTimeTd = $("<td></td>").append(item.course.courseTime);
		var classroomTd = $("<td></td>").append(item.course.classroom);
		var courseWeekTd = $("<td></td>").append(item.course.courseWeek);
		var courseTypeTd = $("<td></td>").append(item.course.courseType);
		var scoreTd = $("<td></td>").append(item.course.score);
		if(item.mark<60){
			var markTd = $("<td></td>").append("<font style='color:red'>"+item.mark+"</font>")
		}else{
			var markTd = $("<td></td>").append(item.mark)
		}
		$("<tr></tr>").append(courseIdTd).append(courseNameTd).append(
				teacherIdTd).append(departmentNameTd).append(courseTimeTd)
				.append(classroomTd).append(courseWeekTd).append(courseTypeTd)
				.append(scoreTd).append(markTd).appendTo("#courses_table tbody");
	});
}