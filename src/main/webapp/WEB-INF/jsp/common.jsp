<%@ page import="com.catcher92.demo.springdemo.constant.UserConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<%--权限--%>
<c:set var="PERMISSIONS_USER_ALL" value="<%=UserConstant.PERMISSIONS_USER_ALL%>"></c:set>
<c:set var="PERMISSIONS_USER_ADD" value="<%=UserConstant.PERMISSIONS_USER_ADD%>"></c:set>
<c:set var="PERMISSIONS_USER_DELETE" value="<%=UserConstant.PERMISSIONS_USER_DELETE%>"></c:set>
<c:set var="PERMISSIONS_USER_UPDATE" value="<%=UserConstant.PERMISSIONS_USER_UPDATE%>"></c:set>
<c:set var="PERMISSIONS_USER_GET" value="<%=UserConstant.PERMISSIONS_USER_GET%>"></c:set>
<script src="${rp}/static/plugins/jquery/jquery.min.js"></script>