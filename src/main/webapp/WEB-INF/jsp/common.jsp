<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String contextPath = request.getContextPath();
    StringBuffer rootPath = new StringBuffer();
    rootPath.append(request.getScheme());
    rootPath.append("://");
    rootPath.append(request.getServerName());
    if(request.getServerPort() != 80) {
        rootPath.append(":");
        rootPath.append(request.getServerPort());
    }
    rootPath.append(contextPath);
%>
<c:set var="basePath" value="<%=rootPath%>"></c:set>
<c:set var="rp" value="<%=rootPath%>"></c:set>
<%--jquery--%>
<script src="http://cdn.bootcss.com/jquery/1.12.4/jquery.js"></script>
<%--bootstrap--%>
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/json2/20140204/json2.min.js"></script>
<![endif]-->
<%--jquery validate--%>
<script src="http://cdn.bootcss.com/jquery-validate/1.16.0/jquery.validate.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-validate/1.15.1/localization/messages_zh.min.js"></script>
<%--jquery form--%>
<script src="http://cdn.bootcss.com/jquery.form/4.1.0/jquery.form.js"></script>