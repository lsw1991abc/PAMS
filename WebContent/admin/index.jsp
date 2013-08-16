<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.security.core.context.SecurityContextImpl"%>
<%@page import="com.lssrc.pams.util.PamsConstant"%>
<%@page import="org.springframework.security.core.GrantedAuthority"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	 String authName = null;
     SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>后台管理--个人事务管理系统  By 李世伟</title>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../user/js/shade.js" type="text/javascript" charset="utf-8"></script>
<script src="../user/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="js/index.js" type="text/javascript"></script>
<script src="js/user.js" type="text/javascript"></script>
<script src="js/lifeWall.js" type="text/javascript"></script>
<script src="js/advice.js" type="text/javascript"></script>
<script src="js/assets.js" type="text/javascript"></script>
<script src="js/message.js" type="text/javascript"></script>
<script src="js/memorandum.js" type="text/javascript"></script>
<script src="js/location.js" type="text/javascript"></script>
<script src="js/jquery.uploadify.js" type="text/javascript" charset="utf-8"></script>
<link type="text/css" href="../css/common.css" rel="stylesheet" />
<link type="text/css" href="../user/css/common.css" rel="stylesheet" />
<link type="text/css" href="../user/css/index.css" rel="stylesheet" />
<link type="text/css" href="../user/css/itemInfo.css" rel="stylesheet" />
<link href="css/uploadify.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="sidebar">
  <div id="logo">logo</div>
  <div id="admin_info">
    <p>您好：
      <sec:authentication property="name" />
    </p>
    <p>用户组：
    <%     
      if(securityContextImpl != null) {
    	  authName = ((List<GrantedAuthority>)securityContextImpl.getAuthentication().getAuthorities()).get(0) + "";
    	  if(PamsConstant.ROLE_ADMIN.equalsIgnoreCase(authName)) {
    		  out.print("管理员");  
    	  } else if(PamsConstant.ROLE_SUPER.equalsIgnoreCase(authName)) {
    		  out.print("超级管理员");  
    	  } else {
    		  out.print("未知用户组");  
    	  }
      } else {
    	  out.print("未知用户组");  
      }
      %>
    </p>
    <p><a href="<%=path %>/user/">&lt;&lt;返回用户区</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pams/j_spring_security_logout">退出</a></p>
  </div>
  <ul id="main-nav">
    <li><span class="focus">首页</span></li>
    <li><span>用户</span>
      <ul>
        <li style="display:none;"><span action="add_user_view">添加</span></li>
        <li><span action="query_user_page">查看所有</span></li>
      </ul>
    </li>
    <li><span>生活墙</span>
      <ul>
        <li><span action="query_lifewall_page">查看所有</span></li>
      </ul>
    </li>
    <li><span>资产类型</span>
      <ul>
      	<li><span action="add_assetsType_view">添加</span></li>
        <li><span action="query_assetsType_page">资产分类</span></li>
      </ul>
    </li>
    <li><span>备忘录</span>
    	<ul>
    		<li><span action="query_memorandum_page">查看提醒事件</span></li>
	        <li style="display:none;"><span action="update_memorandum_set">服务设置</span></li>
	      </ul>
    </li>
    <li><span>用户反馈</span>
      <ul>
        <li><span action="query_advice_page">查看所有</span></li>
      </ul>
    </li>
    <li><span>站内信</span>
      <ul>
        <li><span action="add_message_view">新消息</span></li>
        <li><span action="query_message_page">历史消息</span></li>
      </ul>
    </li>
    <li><span>网站信息</span>
      <ul>
        <li><span action="update_loaction_view">位置</span></li>
      </ul>
    </li>
    <%     
      if(securityContextImpl != null) {
    	  if(PamsConstant.ROLE_SUPER.equalsIgnoreCase(authName)) {
    		  out.print("<li><span>管理员信息</span><ul><li><span action='query_admin_info'>查看所有</span></li></ul></li>");
    	  }
      }
      %>
  </ul>
</div>
<div id="main">
  <div id="main-header" style="display:none;">
    <ul>
      <li><img src="images/icons/paper_content_pencil_48.png" />
        <p>扩展...</p>
      </li>
      <li><img src="images/icons/paper_content_pencil_48.png" />
        <p>扩展...</p>
      </li>
      <li><img src="images/icons/paper_content_pencil_48.png" />
        <p>扩展...</p>
      </li>
      <li><img src="images/icons/paper_content_pencil_48.png" />
        <p>扩展...</p>
      </li>
      <li><img src="images/icons/paper_content_pencil_48.png" />
        <p>扩展...</p>
      </li>
      <li><img src="images/icons/paper_content_pencil_48.png" />
        <p>扩展...</p>
      </li>
      <li><img src="images/icons/paper_content_pencil_48.png" />
        <p>扩展...</p>
      </li>
    </ul>
    <br class="spacer" />
  </div>
  <div id="main-content">
    <div id="main-content-header">
      <h2>首页</h2>
    </div>
    <div id="main-content-body">
      <div class="waiting" style="background:none;text-align:center;font-size:50px;font-weight:bolder;line-height:200px;min-height:440px;">欢迎进入后台管理</div>
    </div>
    <div id="main-content-footer"></div>
  </div>
  <div id="footer" style="background-color:#E0E2E4;color:#000; font-size:15px;"> <small>&#169; Copyright 2012 lssrc.com | Powered by 李世伟</small> </div>
</div>
</body>
</html>
