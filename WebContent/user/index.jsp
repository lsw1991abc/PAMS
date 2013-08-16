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
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<script src="js/shade.js" type="text/javascript" charset="utf-8"></script>
<script src="js/common.js" type="text/javascript" charset="utf-8"></script>
<link type="text/css" href="../css/common.css" rel="stylesheet" />
<link type="text/css" href="css/common.css" rel="stylesheet" />
<link type="text/css" href="css/index.css" rel="stylesheet" />
<link type="text/css" href="css/itemInfo.css" rel="stylesheet" />

<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery-ui-slide.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-timepicker-addon.js"></script>
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css" />

</head>
<body>
<div id="header"></div>
<script type="text/javascript">
	$("#header").load("../header.jsp");
</script>
<div id="main">
  <div id="userPanel">
    <div class="lifeWall userPanel_1">
      <div class="module_title">
        <h2>生活墙</h2>
        <a class="settings" href="app.jsp?app=lifeWall">更多</a><a class="settings new">我也来说两句~~</a></div>
      <div class="module_content"></div>
    </div>
    <div class="easyNote userPanel_1">
      <div class="module_title">
        <h2>简易记事本</h2>
        <a class="settings" href="app.jsp?app=easyNote">所有记事本</a><a class="settings new">新记事本</a></div>
      <div class="module_content"></div>
    </div>
    <br class="spacer" />
    <div class="memorandum userPanel_1">
      <div class="module_title">
        <h2>我的备忘录</h2>
        <a class="settings" href="app.jsp?app=memorandum">更多</a><span class="tag_state focus" style="margin-left:15px;" value="memorandum|进行中">进行中</span><span class="tag_state" value="memorandum|已结束">已结束</span></div>
      <div class="module_content"></div>
    </div>
    <div class="task userPanel_1">
      <div class="module_title">
        <h2>我的任务</h2>
        <a class="settings" href="app.jsp?app=task">更多</a><span class="tag_state focus" style="margin-left:15px;" value="task|进行中">进行中</span><span class="tag_state" value="task|已完成">已完成</span></div>
      <div class="module_content"></div>
    </div>
    <br class="spacer" />
    <div class="dairy userPanel_1">
      <div class="module_title">
        <h2>生活记事</h2>
        <a class="settings" href="app.jsp?app=dairy">所有生活记事</a>
        <a class="settings new">撰写</a>
        </div>
      <div class="module_content"></div>
    </div>
    <div class="transaction userPanel_1">
      <div class="module_title">
        <h2>资金流动记录</h2>
        <a class="settings" href="app.jsp?app=transaction">所有流动记录</a><span class="tag_state focus" style="width:110px; margin-left:15px;" value="transaction|">最近交易记录</span><span class="tag_state" value="transaction|1">收入</span><span class="tag_state" value="transaction|-1">支出</span></div>
      <div class="module_content"></div>
    </div>
    <br class="spacer" />
  </div>
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
