<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Error - 个人事务管理系统</title>
<script src="<%=basePath%>/js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/error.js" type="text/javascript" charset="utf-8"></script>
<link type="text/css" href="<%=basePath%>/css/common.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath%>/css/error.css" rel="stylesheet" />
</head>
<body>
<%
String errorCode = request.getParameter("error");
%>
<div id="header"></div>
<script type="text/javascript">
	$("#header").load("<%=basePath%>/header.jsp");
</script>
<div id="main">
	
	<%
		if("403".equalsIgnoreCase(errorCode)) {
			out.write("<div id='main_content' style='text-align:center;'><span style='font-size:30px; color:red;'>权限不足</span></div>");
		} else if("404".equalsIgnoreCase(errorCode)) {
			out.write("<div id='main_content' style='background:url(image/404.jpg) no-repeat center center;'></div>");
		} else if("logfail".equalsIgnoreCase(errorCode)) {
			out.write("<div id='main_content' style='text-align:center;'><span style='font-size:30px; color:red;line-height:200px;'>用户名或密码不正确</span><br class='spacer' /><a href='index.jsp'>&lt;&lt;返回首页&gt;&gt;</a></div>");
		} else {
			out.write("<script type='text/javascript'>location.href='index.jsp';</script>");
		}
	%>
	
</div>
<div id="footer"></div>
<script type="text/javascript">
	$("#footer").load("<%=basePath%>/footer.jsp");
</script>
</body>
</html>
