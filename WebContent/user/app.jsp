<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人事务管理系统</title>
<script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery-ui.js" type="text/javascript" charset="utf-8"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<script src="js/shade.js" type="text/javascript" charset="utf-8"></script>
<script src="js/common.js" type="text/javascript" charset="utf-8"></script>
<link type="text/css" href="../css/common.css" rel="stylesheet" />
<link type="text/css" href="css/common.css" rel="stylesheet" />
<link type="text/css" href="css/app.css" rel="stylesheet" />
<link type="text/css" href="css/itemInfo.css" rel="stylesheet" />

<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery-ui-slide.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-timepicker-addon.js"></script>
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css" />
<%
String app = request.getParameter("app");
if("addressBook".equalsIgnoreCase(app)) {
	out.print("<script src='js/addressBook.js' type='text/javascript' charset='utf-8'></script>");
} else if("dairy".equalsIgnoreCase(app)) {
	out.print("<script src='js/dairy.js' type='text/javascript' charset='utf-8'></script>");
} else if("easyNote".equalsIgnoreCase(app)) {
	out.print("<script src='js/easyNote.js' type='text/javascript' charset='utf-8'></script>");
} else if("lifeWall".equalsIgnoreCase(app)) {
	out.print("<script src='js/lifeWall.js' type='text/javascript' charset='utf-8'></script>");
} else if("memorandum".equalsIgnoreCase(app)) {
	out.print("<script src='js/memorandum.js' type='text/javascript' charset='utf-8'></script>");
} else if("message".equalsIgnoreCase(app)) {
	out.print("<script src='js/message.js' type='text/javascript' charset='utf-8'></script>");
} else if("task".equalsIgnoreCase(app)) {
	out.print("<script src='js/task.js' type='text/javascript' charset='utf-8'></script>");
} else if("transaction".equalsIgnoreCase(app)) {
	out.print("<script src='js/transaction.js' type='text/javascript' charset='utf-8'></script>");
} else {
	out.print("<script src='js/error.js' type='text/javascript' charset='utf-8'></script>");
}
%>
</head>
<body>
<div id="header"></div>
<script type="text/javascript">
	$("#header").load("../header.jsp");
</script>
<div id="main">
  <div id="userPanel"></div>
  <div id="main_right"></div>
  <script type="text/javascript">
		$("#main_right").load("userRight.jsp");
	</script>
  <br class="spacer" />
</div>
<div id="footer"></div>
<script type="text/javascript">
	$("#footer").load("../footer.jsp");
</script>
</body>
</html>
