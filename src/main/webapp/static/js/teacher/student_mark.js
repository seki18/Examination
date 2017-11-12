$(function(){
	to_page(1);
});

function to_page(pn){
	$.ajax({
		url : "../teacher/students",
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
		var studentNameTd = $("<td></td>").append(item.student.studentName);
		if(item.mark == null){
			var markTd = $("<td></td>").append("未打分");
		}else{
			var markTd = $("<td></td>").append(item.mark);
		}
		var buttonTd = $("<td></td>").append("<button class='btn btn-success btn-xs btn-info' id='mark_btn' studentId='"+item.studentId+"'>打分</button>");
		$("<tr></tr>").append(studentIdTd).append(studentNameTd).append(markTd).append(buttonTd).appendTo(("#students_table tbody"));
	});
}

$(document).on("click","#mark_btn",function(){
	$("#markModel form")[0].reset();
	$("#mark_message").parent().removeClass("has-error");
	$("#mark_message").text("");
	$("#markModel").modal({
		backdrop : "static"
	});
});

$(document).on("click","#mark_submit_btn",function(){
	var mark = $("#mark_input").val();
	var regax = /(^100$)|(^[1-9]?[0-9]$)/
	if(regax.test(mark)){
		$.ajax({
			url : "../teacher/mark",
			data : {
				studentId : $("#mark_btn").attr("studentId"),
				mark : mark
			},
			type : "POST",
			success : function(){
				alert("打分成功！");
				$("#markModel").modal('hide');
				to_page(currentPage);
			}
		});
	}else{
		$("#mark_message").parent().addClass("has-error");
		$("#mark_message").text("分数必须在0~100之间！");
	}
});