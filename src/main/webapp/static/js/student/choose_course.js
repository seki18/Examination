$(function() {
	to_page_with_department(1);
});

function to_page_with_department(pn) {
	$.ajax({
		url : "../student/selectedCourse",
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
		var courseNameTd = $("<td></td>").append(item.courseName);
		var teacherIdTd = $("<td></td>").append(item.teacher.teacherName);
		var departmentNameTd = $("<td></td>").append(
				item.department.departmentName);
		var courseTimeTd = $("<td></td>").append(item.courseTime);
		var classroomTd = $("<td></td>").append(item.classroom);
		var courseWeekTd = $("<td></td>").append(item.courseWeek);
		var courseTypeTd = $("<td></td>").append(item.courseType);
		var scoreTd = $("<td></td>").append(item.score);
		var buttonTd = $("<td></td>").append("<button class='btn btn-danger btn-xs btn-info' onclick='back_course("+item.courseId+")'>退课</button>");
		$("<tr></tr>").append(courseIdTd).append(courseNameTd).append(
				teacherIdTd).append(departmentNameTd).append(courseTimeTd)
				.append(classroomTd).append(courseWeekTd).append(courseTypeTd)
				.append(scoreTd).append(buttonTd).appendTo("#courses_table tbody");
	});
}

function back_course(courseId){
	$.ajax({
		url : "../student/backCourse/"+courseId,
		type : "DELETE",
		success : function(result){
			if(result.code == 200){
				alert("退课成功！");
				to_page_with_department(currentPage);
			}else if(result.code == 400){
				alert("退课失败！");
			}
		},
		error : function(){
			alert("请求失败！");
		}
	});
}
