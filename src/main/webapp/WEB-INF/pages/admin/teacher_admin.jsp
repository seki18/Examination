<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%>
<script src="${PATH }/static/js/jquery-3.2.1.min.js"></script>
<script src="${PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link href="${PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<title>教务管理系统(管理员)</title>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="../teacher/update_info.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked" id="nav">
					<li><a href="../admin/course_admin">课程管理</a></li>
					<li><a href="../admin/student_admin">学生管理</a></li>
					<li class="active"><a href="../admin/teacher_admin">教师管理</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<div class="row">
							<h2 style="font-family: 黑体" class="col-md-2">教师管理</h2>
						</div>
					</div>

					<!-- Table -->
					<table class="table" id="teachers_table">
						<thead>
							<tr>
								<th>教师ID</th>
								<th>姓名</th>
								<th>性别</th>
								<th>出生日期</th>
								<th>入职日期</th>
								<th>学历</th>
								<th>职称</th>
								<th>院系</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>

					<div class="panel-footer">
						<nav id="page_nav" style="text-align: center"></nav>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
<script src="${PATH }/static/js/index/nav.js"></script>
<script src="${PATH }/static/js/admin/teacher_admin.js"></script>
</html>