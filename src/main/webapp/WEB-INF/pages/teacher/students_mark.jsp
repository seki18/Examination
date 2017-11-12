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
<title>教务管理系统(教师)</title>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="update_info.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked" id="nav">
					<li class="active"><a href="#" id="my_course">学生成绩</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<div class="row">
							<h2 style="font-family: 黑体" class="col-md-2">学生成绩</h2>
						</div>
					</div>
					<!-- Table -->
					<table class="table" id="students_table">
						<thead>
							<tr>
								<th>学号</th>
								<th>学生姓名</th>
								<th>分数</th>
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
	
	<div class="modal fade" id="markModel" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">打分</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" method="post">
					<div class="form-group">
						<label for="mark_input" class="col-sm-2 control-label">分数</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="mark_input"
								name="mark">
							<span id="mark_message"></span>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary" id="mark_submit_btn">提交</button>
			</div>

		</div>
	</div>
</div>
</body>
<script src="${PATH }/static/js/teacher/student_mark.js"></script>
<script src="${PATH }/static/js/teacher/update_info.js"></script>
<script src="${PATH }/static/js/index/nav.js"></script>
</html>