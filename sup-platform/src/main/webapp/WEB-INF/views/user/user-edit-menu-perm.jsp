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
	<div class="page-content no-padding" id="wrapper-content" style="background-color: #fff;">
			<form class="form-horizontal" role="form">
				<input type="hidden" name="userId"  value="${userId}"/>
				<div id="perm-data-list-tree"></div>
				<div class="clearfix form-actions col-xs-12">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type="button" id="edit-user-menu-perm-submit-btn">
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
</body>
</html>
