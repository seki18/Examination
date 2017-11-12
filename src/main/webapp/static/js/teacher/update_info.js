function update_info(){
	$("#updateModel form")[0].reset();
	//1.表单回显
	$.ajax({
		url : "../teacher/info",
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
		url : "../teacher/update",
		data : $("#updateModel form").serialize(),
		type : "PUT",
		success : function(result){
			alert("更新成功！");
			$("#updateModel").modal('hide');
			to_page_with_teacher(currentPage);
		}
	});
});

