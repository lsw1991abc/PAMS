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
%>
<div class="main">
  <div class="logo"></div>
  <div class="nav">
    <ul>
      <%     
      SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
      if(securityContextImpl != null) {
    	  Authentication  authentication = securityContextImpl.getAuthentication();
    	  %>
      	  <li><a href="<%=path %>/contact/">帮助</a></li>
      	  <li class="li_split">|</li>
    	  <li class="shortcut"><a style="cursor:pointer">快捷面板</a>
    	  	<ul>
    	  		<li><a href="<%=path %>/user/app.jsp?app=memorandum">备忘录</a></li>
    	  		<li><a href="<%=path %>/user/app.jsp?app=task">任务</a></li>
    	  		<li><a href="<%=path %>/user/app.jsp?app=dairy">生活记事</a></li>
    	  		<li><a href="<%=path %>/user/app.jsp?app=easyNote">简易记事本</a></li>
    	  		<li><a href="<%=path %>/user/app.jsp?app=lifeWall">生活墙</a></li>
    	  		<li><a href="<%=path %>/user/app.jsp?app=transaction">记账本</a></li>
    	  		<li><a href="<%=path %>/user/app.jsp?app=addressBook">通讯录</a></li>
    	  	</ul>
    	  </li>
    	  <li><a href="<%=path %>/user/"><%=authentication.getName()%></a></li>
    	  <li><a href="<%=path %>/user/app.jsp?app=message">信箱</a></li>
    	  <li><a href="<%=path %>/j_spring_security_logout">退出</a></li>
    	  <script type="text/javascript">
			$(".nav ul .shortcut").hover(function() {
				$(this).children("ul").show();
			}, function() {
				$(this).children("ul").hide();
			});
        	</script>
    	  <%
    	  List<GrantedAuthority> authorities = (List<GrantedAuthority>)authentication.getAuthorities();
    	  String authName = authorities.get(0) + "";
    	  if(PamsConstant.ROLE_ADMIN.equalsIgnoreCase(authName) || PamsConstant.ROLE_SUPER.equalsIgnoreCase(authName)) {
    		  out.print("<li class='li_split'>|</li><li><a href='" + path + "/admin/'>后台管理>></a></li>");  
    	  }
      } else {
    	  %>
    	  <li><a href="<%=path %>/">首页</a></li>
    	  <li><a href="<%=path %>/contact/">帮助</a></li>
      	  <li class="li_split">|</li>
    	  <li><a href="<%=path %>/">登陆</a></li>
    	  <li><a href="<%=path %>/register.jsp">注册</a></li>
    	  <%  
      }
      %>
    </ul>
  </div>
</div>