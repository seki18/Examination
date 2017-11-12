function update_info(){
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
		url : "../student/info",
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
		url : "../student/update",
		data : $("#updateModel form").serialize(),
		type : "PUT",
		success : function(result){
			alert("更新成功！");
			$("#updateModel").modal('hide');
			to_page_with_department(currentPage);
		}
	});
});

