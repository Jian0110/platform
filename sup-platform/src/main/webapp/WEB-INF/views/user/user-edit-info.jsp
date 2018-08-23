<%--
  Created by IntelliJ IDEA.
  User: immor
  Date: 2017/12/10
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <%@include file="../css-common.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user-list.css"/>

</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-user-info" id="breadcrumbs">
            <!-- /.breadcrumb -->
            <ul class="breadcrumb">
                <li>个人设置</li>
                <li>信息查看</li>
            </ul>
        </div>
        <div class="page-content no-padding" id="wrapper-content">
            <div class="padding-top-15px">
                <input type="file" id="user-image-input" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg"/>
                <form class="form-horizontal" role="form" id="user-info-edit-form">
                    <input type="hidden" id="userId" name="userId" value="${user.userId}">
                    <input type="hidden" id="default_password" name="default_password" value="${user.password}">
                    <h3 class="header smaller lighter blue col-md-12">基本信息
                        <small>姓名角色及头像等信息</small>
                    </h3>
                    <div class="row no-margin padding-top-15px">
                        <div class="col-sm-3 center">
							<span class="profile-picture">
								<c:choose>
                                    <c:when test="${user.image == null || user.image == ''}">
                                        <img class="editable img-responsive" alt="头像" id="image"
                                             src="${pageContext.request.contextPath}/assets/vendor/ace/images/avatars/profile-pic.jpg">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="editable img-responsive" alt="头像" id="image" src="${user.image}">
                                    </c:otherwise>
                                </c:choose>
								<input type="hidden" name="image" id="avatar-base64-input" value="${user.image}"/>
							</span>
                        </div>
                        <div class="col-sm-9 center">
                            <div class="row" id="username">
                                <div class="form-group col-sm-6">
                                    <label class="col-sm-4 control-label no-padding-right text-right"> 用户名:</label>
                                    <div class="col-sm-8">
                                        <div class="input-group col-sm-12 underline">
                                            ${user.username}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row" id="realname">
                                <div class="form-group col-sm-6">
                                    <label class="col-sm-4 control-label no-padding-right text-right"> 真实姓名:</label>
                                    <div class="col-sm-8">
                                        <div class="input-group col-sm-12 underline">
                                            ${user.realname}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row" id="rolename">
                                <div class="form-group col-sm-6">
                                    <label class="col-sm-4 control-label no-padding-right text-right">角色:</label>
                                    <div class="col-sm-8">
                                        <div class="input-group col-sm-12 underline">
                                            ${user.roleName}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row" id="department">
                                <div class="form-group col-sm-6">
                                    <label class="col-sm-4 control-label no-padding-right text-right">部门:</label>
                                    <div class="col-sm-8">
                                        <div class="input-group col-sm-12 underline">
                                            ${user.departmentName}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <h3 class="header smaller lighter blue col-xs-12">密码修改
                        <small>个人密码修改</small>
                    </h3>
                    <div class="row no-margin" >
                        <div class="form-group col-sm-6 col-sm-push-2">
                            <label class="col-sm-4 control-label no-padding-right text-right">密码 </label>
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input id="password" name="password" type="text" placeholder="请输入6-12位密码，不修改请留空"
                                           class="form-control" value="">
                                    <span class="input-group-addon">
											<i class="ace-icon glyphicon glyphicon-lock"></i>
										</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix form-actions col-sm-12 no-margin-top">
                        <div class="col-md-12 no-padding-left text-center">
                            <button class="btn btn-info" type="button" id="edit-user-submit-btn">
                                <i class="ace-icon fa fa-check bigger-110"></i> 提交
                            </button>

                            &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
                            <button class="btn" type="reset">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="../javascript-common.jsp" %>
<script src="${pageContext.request.contextPath}/assets/js/user-edit.js"></script>
</body>
</html>
