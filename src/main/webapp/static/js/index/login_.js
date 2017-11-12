$("#login_btn").click(function() {
	$("#loginModel form")[0].reset();
	$("#login_message").parent().removeClass("has-error");
	$("#login_message").text("");
	$("#loginModel").modal({
		backdrop : "static"
	});
});
$(document).on("click", "#login_action", function() {
	var username = $("#username_input").val();
	var password = $("#password_input").val();
	$.ajax({
		url:"login",
		data:{
			username:username,
			password:password
		},
		type:"POST",
		success:function(result){
			if(result.code == 200){
				window.location.href = "index.jsp";
			}else if(result.code == 210){
				window.location.href = "admin/course_admin";
			}else if(result.code == 220){
				window.location.href = "teacher/my_course";
			}else if(result.code == 230){
				window.location.href = "student/all_course";
			}else if(result.code == 400){
				login_message();
			}
		},
		error:function(result){
			alert("登录失败");
		}
	});
});
function login_message(){
	$("#login_message").parent().removeClass("has-error");
	$("#login_message").text("");
	$("#login_message").parent().addClass("has-error");
	$("#login_message").text("用户名或密码输入错误");
}
