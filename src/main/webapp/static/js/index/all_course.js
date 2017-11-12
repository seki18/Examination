$(function() {
	to_page(1);
	get_department();
});


function to_page(pn) {
	$("#department_btn").children("li").removeClass("active");
	$.ajax({
		url : "courses",
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


function to_page_with_department(pn, departmentId) {
	$("#department_btn").children("li").removeClass("active");
	$.ajax({
		url : "courses/" + departmentId,
		data : {
			pn : pn,
			departmentId : departmentId
		},
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
		$("<tr></tr>").append(courseIdTd).append(courseNameTd).append(
				teacherIdTd).append(departmentNameTd).append(courseTimeTd)
				.append(classroomTd).append(courseWeekTd).append(courseTypeTd)
				.append(scoreTd).appendTo(
						"#courses_table tbody");
	});
}

function get_department() {
	$("#department_btn").empty();
	$
			.ajax({
				url : "departments",
				type : "GET",
				success : function(result) {
					$.each(result.map.departments, function(index, item) {
						$("<li></li>").attr("onclick",
								"$(this).addClass('active')").append(
								$("<a></a>").append(item.departmentName).attr(
										"href", "javascript:void(0)").attr(
										"onclick",
										"to_page_with_department(1,"
												+ item.departmentId + ")"))
								.appendTo("#department_btn");
					});
					$("#department_btn")
							.append(
									"<li role='separator' class='divider'></li>")
							.append(
									"<li><a href='javascript:void(0)' onclick='to_page(1)'>全部</a></li>");
				}
			});
}

$("#all_course").click(function() {
	to_page(1);
});

