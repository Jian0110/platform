<%--
  Created by IntelliJ IDEA.
  User: ycc
  Date: 2017/12/10
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
	<head>
		
		<title>嘉华ERP供应商扩展平台</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/favicon.ico"/>
		
		<!-- bootstrap & fontawesome --> 
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/font-awesome-4.7.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/layer/theme/default/layer.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/jquery.gritter/jquery.gritter.min.css"/>
		<!-- page specific plugin styles -->

		<!-- text fonts -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/ace/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<!-- inline styles related to this page -->



		<script>
			var $contextPath = '${pageContext.request.contextPath}';
		</script>

	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default ace-save-state navbar-fixed-top">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>

				<div class="navbar-header pull-left">
					<a class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							供应商扩展平台
						</small>
					</a>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!--
						<li class="grey dropdown-modal">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-tasks"></i>
								<span class="badge badge-grey">0</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-check"></i>
									0 待完成任务
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar">
										
									</ul>
								</li>

								<li class="dropdown-footer">
									<a href="#">
										查看全部任务
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						-->
						<li class="purple dropdown-modal">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-bell icon-animated-bell"></i>
								<span class="badge badge-important" id="tip-count-span1">0</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-exclamation-triangle"></i>
									<span id="tip-count-span2"></span>
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar navbar-pink" id="tip-list-ul">

									</ul>
								</li>

								<li class="dropdown-footer">
									<a href="#" id="tip-list-all-a">
										查看全部提醒
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						<!--
						<li class="green dropdown-modal">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">0</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-envelope-o"></i>
									0 消息
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar">
										
									</ul>
								</li>

								<li class="dropdown-footer">
									<a href="inbox.html">
										查看全部消息
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						-->
						<li class="light-blue dropdown-modal">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
							<c:choose>
								<c:when test="${photo== null || photo == ''}">
									<img class="nav-user-photo" id="user-photo" src="${pageContext.request.contextPath}/assets/vendor/ace/images/avatars/profile-pic.jpg" alt="Jason's Photo" />
								</c:when>
								<c:otherwise>
									<img class="nav-user-photo" id="user-photo" src="${photo}"/>
								</c:otherwise>
							</c:choose>
								<%-- <img class="nav-user-photo" id="user-photo" src="${pageContext.request.contextPath}/assets/vendor/ace/images/avatars/user.jpg" alt="Jason's Photo" />
								 --%><span class="user-info">
									<small>欢迎你</small>
									<shiro:user>
											<shiro:principal property="realname"/>
									</shiro:user>
									<shiro:guest>
										游客
									</shiro:guest>
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<!--
								<li>
									<a href="#">
										<i class="ace-icon fa fa-cog"></i>
										系统设置
									</a>
								</li>
								-->
								<li>
									<a href="#" id="user-info">
										<i class="ace-icon fa fa-user"></i>
										个人信息
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="${pageContext.request.contextPath}/logout">
										<i class="ace-icon fa fa-power-off"></i>
										退出登录
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div><!-- /.navbar-container -->
		</div>

		<div class="main-container ace-save-state" id="main-container">
			<div id="sidebar" class="sidebar responsive ace-save-state sidebar-fixed sidebar-scroll">
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>
						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>
						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>
						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>
						<span class="btn btn-info"></span>
						<span class="btn btn-warning"></span>
						<span class="btn btn-danger"></span>
					</div>
				</div><!-- /.sidebar-shortcuts -->

				<ul class="nav nav-list menu-nav-list" id="menu-nav-list">
					<li class="active">
						<a href="javascript:;" data-href="index.html" data-key="dashboard">
							<i class="menu-icon fa fa-tachometer"></i>
							<span class="menu-text"> 总览面版 </span>
							<!--<span class="badge badge-primary">5</span>-->
						</a>
					</li>
				</ul><!-- /.nav-list -->

				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>

			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<div class="row"> 
							<div id="page-content-tab">
								<ul class="nav nav-tabs tab-title-ul">
									<li class="active">
										<a data-toggle="tab" href="#dashboard">
											<i class="green ace-icon fa fa-tachometer bigger-120"></i> 
											<span class="tab-title">总览面版</span>
											<span class="tab-close-btn"><i class="red ace-icon glyphicon glyphicon-remove"></i></span>
										</a>
									</li>
								</ul>
								<div class="tab-content no-padding">
									<div id="dashboard" class="tab-pane fade in active">
										<!-- <a class="btn btn-default pull-right" id="websocket-test-btn"> webSocket </a>-->
									</div>
								</div>
							</div>
						</div>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-2.1.4.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/jquery.validate/jquery.validate.min.js"></script>
		<!-- <![endif]-->

		<!--[if IE]>
			<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-1.11.3.min.js"></script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/assets/vendor/jquery/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<script src="${pageContext.request.contextPath}/assets/vendor/jquery-ui/jquery-ui.custom.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/jquery-ui/jquery.ui.touch-punch.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/layer/layer.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/jquery.gritter/jquery.gritter.min.js"></script>

		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/assets/vendor/ace/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/ace/js/ace.js"></script>


		
		<!-- custom scripts -->
		<script src="${pageContext.request.contextPath}/assets/js/index.js?r=<%=Math.random()%>"></script>
		
	</body>
</html>
