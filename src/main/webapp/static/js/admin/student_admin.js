var studentId;

$(function(){
	to_page(1);
});

function to_page(pn){
	$.ajax({
		url : "../admin/students",
		data : "pn=" + pn,
		type : "GET",
		success : function(result){
			// 1.显示学生数据
			build_students_table(result);
			// 2.显示分页条
			build_page_nav(result);
		}
	});
}

function build_students_table(result){
	$("#students_table tbody").empty();
	var students = result.map.pageInfo.list;
	$.each(students,function(index,item){
		var studentIdTd = $("<td></td>").append(item.studentId);
		var studentNameTd = $("<td></td>").append(item.studentName);
		var sexTd = $("<td></td>").append(item.sex);
		var birthdayTd = $("<td></td>").append(item.birthdayStr);
		var gradeTd = $("<td></td>").append(item.gradeStr);
		var departmentTd = $("<td></td>").append(item.department.departmentName);
		var editBotton = "<button class='btn btn-success btn-xs btn-info' onclick='edit_student("+item.studentId+")'>修改</button>";
		var deleteBotton = "<button class='btn btn-danger btn-xs btn-info' onclick='delete_student("+item.studentId+")'>删除</button>";
		var bottonTd = $("<td></td>").append(editBotton).append(" ").append(deleteBotton);
		$("<tr></tr>").append(studentIdTd).append(studentNameTd).append(sexTd).append(birthdayTd).append(gradeTd).append(departmentTd).append(bottonTd).appendTo(("#students_table tbody"));
	});
}

function edit_student(studentId){
	this.studentId = studentId;
	$("#updateModel form")[0].reset();
	//1.表单回显
	$("#department_update").empty();
	$.ajax({
		url : "../departments",
		type : "GET",
		success : function(result) {
			$.each(result.map.departments, function(index, item) {
				var option = $("<option></option>").append(
						item.departmentName).attr("value", item.departmentId);
				option.appendTo("#department_update");
			})
		}
	});
	$.ajax({
		url : "../admin/studentInfo",
		data : "studentId="+studentId,
		type : "GET",
		success : function(result){
			var studentData = result.map.student;
			$("#name_update").val(studentData.studentName);
			$("#updateModel input[name=sex]").val([studentData.sex]);
			$("#birthday_update").val(studentData.birthdayStr);
			$("#grade_update").val(studentData.gradeStr);
			$("#department_update").val([studentData.departmentId]);
		}
	});
	$("#updateModel").modal({
		backdrop : "static"
	});
}

$("#update_btn").click(function(){
	$.ajax({
		url : "../admin/studentUpdate?studentId="+studentId,
		data : $("#updateModel form").serialize(),
		type : "PUT",
		success : function(result){
			alert("更新成功！");
			$("#updateModel").modal('hide');
			to_page(currentPage);
		}
	});
});

