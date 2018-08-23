<%--
  Created by IntelliJ IDEA.
  User: ycc
  Date: 2018/5/3
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>通知列表</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="../css-common.jsp"%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tip-list.css"/>

</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
                    <li>个人设置</li>
					<li>通知中心</li>
				</ul><!-- /.breadcrumb -->
			</div>
			<div class="page-content no-padding" id="wrapper-content">
                <div>
                    <table id="dynamic-table" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%" >
                        <thead>
                            <tr>
                                <th></th>
                                <th>提醒类型</th>
                                <th>提醒内容</th>
                                <th>提醒时间</th>
                                <th>是否已读</th>
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

    <!-- custom scripts -->
    <script src="${pageContext.request.contextPath}/assets/js/tip-list.js"></script>
</body>
</html>
