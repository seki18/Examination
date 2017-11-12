<!-- 修改信息框 -->
<div class="modal fade" tabindex="-1" role="dialog" id="updateModel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">修改个人信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="name_update" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name_update" name="studentName">
						</div>
					</div>
					<div class="form-group">
						<label for="sex_update" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-10">
							<label class="sex_update"> 
							<input type="radio" name="sex" value="男">
								男
							</label> 
							<label class="sex_update"> 
							<input type="radio" name="sex" value="女">
								女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="birthday_update" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-10">
							<input type=date class="form-control" id="birthday_update" name="birthdayStr">
						</div>
					</div>
					<div class="form-group">
						<label for="grade_update" class="col-sm-2 control-label">入学日期</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="grade_update" name="gradeStr">
						</div>
					</div>
					<div class="form-group">
						<label for="department_update" class="col-sm-2 control-label">院系</label>
						<div class="col-sm-4">
							<select class="form-control" name="departmentId"
								id="department_update"></select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer form-group">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary" id="update_btn" autofocus="autofocus">修改</button>
			</div>
		</div>
	</div>
</div>
