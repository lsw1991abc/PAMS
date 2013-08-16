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
<link type="text/css" href="../css/common.css" rel="stylesheet" />
<link type="text/css" href="css/index.css" rel="stylesheet" />
</head>
<body>
<div id="header"></div>
<div id="main">
  <div class="main_head">
    <ul>
      <li class="focus">帮助中心</li>
      <li>商务合作</li>
      <li>关于我们</li>
    </ul>
  </div>
  <div class="main_body">
    <div class="main_left">
      <ul>
        <li>帮助中心</li>
        <li>常见问题</li>
        <li>服务协议</li>
      </ul>
      <ul>
        <li>商务合作</li>
      </ul>
      <ul>
        <li>关于我们</li>
        <li>联系我们</li>
      </ul>
    </div>
    <div class="main_content">
      <div>帮助中心<br />
      </div>
      <div>常见问题</div>
      <div>服务协议</div>
      <div>商务合作</div>
      <div>关于我们</div>
      <div><p>QQ：357966261</p><p>E-mail：<a href="mailto:lsw1991abc@163.com">lsw1991abc@163.com</a></p></div>
    </div>
    <br class="spacer" />
  </div>
</div>
<div id="footer"></div>
</body>
</html>
