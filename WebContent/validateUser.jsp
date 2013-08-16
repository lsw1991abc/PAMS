<%@page import="com.lssrc.pams.util.DBUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String username = request.getParameter("username");
	if(username != null && username != "") {
		username = username.toString();
		List users = DBUtils.queryForList("select username from pams_user where username=?", new Object[]{username});
		if(users.size() == 0) {
			out.print("0");
		} else {
			out.print("1");
		}
	}
%>
