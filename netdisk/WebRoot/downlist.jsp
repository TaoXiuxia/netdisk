<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
	<h1>下载列表</h1><hr>
	<c:forEach items="${requestScope.list }" var="r">
    	文件名:${r.realname }<br>
    	上传时间:${r.uploadtime }<br>
    	上传者ip:${r.ip }<br>
    	描述信息:${r.description }<br>
		<a href="${pageContext.request.contextPath }/servlet/DownServlet?id=${r.id}">下载</a><br><hr>
	</c:forEach>
</body>
</html>
