<%--
  Created by IntelliJ IDEA.
  User: immor
  Date: 2017/12/31
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var $contextPath = '${pageContext.request.contextPath}';
</script>
<!-- basic scripts -->
<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-2.1.4.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/assets/vendor/jquery/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/breadcrumb/jquery.breadcrumb.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/ace/js/bootstrap-multiselect.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.ajax.custom.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/jstree/jstree.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/jquery.form/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.commons.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap-datepicker/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/jquery.chosen/chosen.jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/jquery.validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/jquery.validate/emplInfoUtils.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/jquery.validate/validate-methods.js"></script>
