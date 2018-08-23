<%--
  Created by IntelliJ IDEA.
  User: immor
  Date: 2017/12/10
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%@include file="../css-common.jsp"%>


</head>
<body>
	<div class="page-content no-padding" id="wrapper-content">
	
		<div id="department-choese-tree" style="display:none"></div>
		<div style="padding-top:15px;">
			<form class="form-horizontal" role="form" id="user-edit-form">
				<input type="hidden" name="userId"  value="0"/>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="edit-username"> 用户名 </label>
					<div class="col-sm-9">
						<div class="col-xs-12 col-sm-5">
							<input type="text" id="edit-username" name="username" placeholder="用户名" class="form-control"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="edit-realname"> 真实姓名 </label>
					<div class="col-sm-9">
						<div class="col-xs-12 col-sm-5">
							<input type="text" id="edit-realname" name="realname" placeholder="真实姓名" class="form-control"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="edit-role"> 角色 </label>
					<div class="col-sm-9">
						<div class="col-xs-12 col-sm-5">
							<select class="form-control" id="edit-role" name="roleId">
								<c:forEach items="${roleList }" var="item">
									<c:if test="${item.enable}"><option value="${item.roleId }">${item.name }</option></c:if>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="edit-department-show"> 部门 </label>
					<div class="col-sm-9">
						<div class="col-xs-12 col-sm-5">
							<input type="text" id="edit-department-show" name="departmentName" readonly="readonly" class="form-control" placeholder="所属部门"/>
							<input type="hidden" id="edit-department-hidden" name="departmentId" value="0"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="edit-state"> 状态 </label>
					<div class="col-sm-9">
						<div class="col-xs-12 col-sm-5">
							<select class="form-control" id="edit-state" name="enable">
								<option value="true">启用</option>
								<option value="false">停用</option>
							</select>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 密码 </label>
					<div class="col-sm-9">
						<div class="col-xs-12 col-sm-5">
							<input type="text" name="password" id="form-field-2" placeholder="不修改请留空" class="form-control"/>
						</div>
					</div>
				</div>
	
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type="button" id="edit-user-submit-btn">
							<i class="ace-icon fa fa-check bigger-110"></i> 提交
						</button>
	
						&nbsp; &nbsp; &nbsp;
						<button class="btn" type="reset">
							<i class="ace-icon fa fa-undo bigger-110"></i> 重置
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
