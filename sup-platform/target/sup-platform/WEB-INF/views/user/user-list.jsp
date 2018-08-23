<%--
  Created by IntelliJ IDEA.
  User: immor
  Date: 2017/12/10
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>系统用户列表</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="../css-common.jsp"%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user-list.css"/>

</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>系统管理</li>
                    <li>用户管理</li>
				</ul><!-- /.breadcrumb -->
			</div>
			<div class="page-content no-padding" id="wrapper-content">
                <div>
                    <form class="form-inline" id="search-form-block" role="form">
                        <div class="form-group col-md-2 col-sm-2 col-xs-6 no-padding">
                            <label class="col-sm-12" for="search-username"> 用户名： </label>
                            <div class="col-sm-12">
                                <input type="text" id="search-username" placeholder="用户名" name="username"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-2 col-xs-6 no-padding">
                            <label class="col-sm-12" for="search-realname"> 真实姓名： </label>
                            <div class="col-sm-12">
                                <input type="text" id="search-realname" placeholder="真实姓名" name="realname"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-2 col-xs-6 no-padding">
                            <label class="col-sm-12" for="search-state">账号状态： </label>
                            <div class="col-sm-12">
                                <select id="search-state" class="multiselect" multiple="" name="state">
                                    <option value="0">停用</option>
                                    <option value="1">正常</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-2 col-xs-6">
                            <label class="col-sm-12">&nbsp;</label>
                            <div class="col-sm-12">
                                <a class="btn btn-white btn-primary" id="search-btn"><i class="fa fa-search bigger-140"></i></a>
                            </div>
                        </div>
                    </form>

                    <table id="dynamic-table" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%" >
                        <thead>
                            <tr>
                                <th></th>
                                <th>用户名</th>
                                <th>真实姓名</th>
                                <th>角色</th>
                                <th>所属部门</th>
                                <th>账号状态</th>
                                <th>最后登录</th>
                                <th>记录操作</th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
			</div>
		</div>
	</div>

    <%@include file="../javascript-common.jsp"%>
    <%@include file="../javascript-datatable.jsp"%>
		
    <script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.fillArea.js"></script>
    <!-- custom scripts -->
    <script src="${pageContext.request.contextPath}/assets/js/user-list.js?r=<%=Math.random()%>"></script>
</body>
</html>
