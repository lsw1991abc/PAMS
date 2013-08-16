<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Login.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="<%=path%>/js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#verifyUserName").click(function() {
		$.get("validateUser.jsp", {username:$("#userName").val()}, function(data) {
			console.log(data);	
		});
	});
});
</script>
</head>

<body>
<input type="text" id="userName" /><span id="verifyUserName" style="display:block;color:red;cursor:pointer; width:100px;">校验</span>
<hr />
<!-- 
	<form name="f" action="j_spring_security_check" method="POST">
      <table>
        <tr><td>User:</td><td><input type='text' name='j_username' value="aaa" /></td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password' value="aaa"></td></tr>

        <tr><td colspan='2'><input name="submit" type="submit"></td></tr>
      </table>

    </form>
     -->
</body>
</html>
