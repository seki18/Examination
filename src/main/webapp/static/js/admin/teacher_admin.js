var teacherId;

$(function(){
	to_page(1);
});

function to_page(pn){
	$.ajax({
		url : "../admin/teachers",
		data : "pn=" + pn,
		type : "GET",
		success : function(result){
			// 1.显示教师数据
			build_students_table(result);
			// 2.显示分页条
			build_page_nav(result);
		}
	});
}

function build_students_table(result){
	$("#teachers_table tbody").empty();
	var students = result.map.pageInfo.list;
	$.each(students,function(index,item){
		var studentIdTd = $("<td></td>").append(item.teacherId);
		var studentNameTd = $("<td></td>").append(item.teacherName);
		var sexTd = $("<td></td>").append(item.sex);
		var birthdayTd = $("<td></td>").append(item.birthdayStr);
		var gradeTd = $("<td></td>").append(item.gradeStr);
		var degreeTd = $("<td></td>").append(item.degree);
		var titleTd = $("<td></td>").append(item.title);
		var departmentTd = $("<td></td>").append(item.department.departmentName);
		var editBotton = "<button class='btn btn-success btn-xs btn-info' onclick='edit_teacher("+item.teacherId+")'>修改</button>";
		var deleteBotton = "<button class='btn btn-danger btn-xs btn-info' onclick='delete_teacher("+item.teacherId+")'>删除</button>";
		var bottonTd = $("<td></td>").append(editBotton).append(" ").append(deleteBotton);
		$("<tr></tr>").append(studentIdTd).append(studentNameTd).append(sexTd).append(birthdayTd).append(gradeTd).append(degreeTd).append(titleTd).append(departmentTd).append(bottonTd).appendTo(("#teachers_table tbody"));
	});
}

function edit_teacher(teacherId){
	this.teacherId = teacherId;
	$("#updateModel form")[0].reset();
	//1.表单回显
	$.ajax({
		url : "../admin/teacherInfo",
		data : "teacherId="+teacherId,
		type : "GET",
		success : function(result){
			var teacherData = result.map.teacher;
			$("#name_update").val(teacherData.teacherName);
			$("#updateModel input[name=sex]").val([teacherData.sex]);
			$("#birthday_update").val(teacherData.birthdayStr);
			$("#grade_update").val(teacherData.gradeStr);
			$("#department_update").text(teacherData.department.departmentName);
			$("#degree_update").val([teacherData.degree]);
			$("#title_update").val([teacherData.title]);
		}
	});
	$("#updateModel").modal({
		backdrop : "static"
	});
}

$("#update_btn").click(function(){
	$.ajax({
		url : "../admin/teacherUpdate?teacherId="+teacherId,
		data : $("#updateModel form").serialize(),
		type : "PUT",
		success : function(result){
			alert("更新成功！");
			$("#updateModel").modal('hide');
			to_page(currentPage);
		}
	});
});
