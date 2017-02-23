<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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