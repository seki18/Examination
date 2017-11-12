<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="static/js/jquery-3.2.1.min.js"></script>
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<title>教务管理系统(游客)</title>
</head>
<body>
	<jsp:include page="menu/top.jsp"></jsp:include>
	<jsp:include page="menu/login.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked" id="nav">
					<li class="active"><a href="#" id="all_course">所有课程</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<div class="row">
							<h2 style="font-family: 黑体" class="col-md-2">课程列表</h2>
							<div class="btn-group" style="margin-top: 20px">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									院系 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" id="department_btn">

								</ul>
							</div>
						</div>
					</div>

					<!-- Table -->
					<table class="table" id="courses_table">
						<thead>
							<tr>
								<th>课程号</th>
								<th>课程名称</th>
								<th>授课老师</th>
								<th>院系</th>
								<th>上课时间</th>
								<th>上课地点</th>
								<th>周数</th>
								<th>课程类型</th>
								<th>学分</th>
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
<script src="static/js/index/all_course.js"></script>
<script src="static/js/index/login_.js"></script>
<script src="static/js/index/nav.js"></script>
</html>