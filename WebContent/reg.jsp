<%@page import="org.springframework.security.authentication.encoding.Md5PasswordEncoder"%>
<%@page import="com.lssrc.pams.util.UtilFactory"%>
<%@page import="com.lssrc.pams.util.PamsConstant"%>
<%@page import="com.lssrc.pams.util.DBUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	String username = request.getParameter("username")==null?"":request.getParameter("username").toString();
	String password = request.getParameter("password")==null?"":request.getParameter("password").toString();
	String realName = request.getParameter("realName")==null?"":request.getParameter("realName").toString();
	String idCard = request.getParameter("idCard")==null?"":request.getParameter("idCard").toString();
	String telephone = request.getParameter("telephone")==null?"":request.getParameter("telephone").toString();
	String email = request.getParameter("email")==null?"":request.getParameter("email").toString();
	String qq = request.getParameter("qq")==null?"":request.getParameter("qq").toString();
	String birthDate = request.getParameter("birthDate")==null?"":request.getParameter("birthDate").toString();
	String birthLocation = request.getParameter("birthLocation")==null?"":request.getParameter("birthLocation").toString();
	String liveLocation = request.getParameter("liveLocation")==null?"":request.getParameter("liveLocation").toString();
	String livePlace = request.getParameter("livePlace")==null?"":request.getParameter("livePlace").toString();
	String registerTime = UtilFactory.getDateUtil().getRightTime();
	String signature = "";
	String authority = PamsConstant.ROLE_USER;
	
	birthLocation = birthLocation.replaceAll("--请选择--", "");
	liveLocation = liveLocation.replaceAll("--请选择--", "");
	
	Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
	password = md5Encoder.encodePassword(password, null);
	
	if(DBUtils.update("insert into pams_user(username, password, realName, idCard, telephone, email, qq, birthDate, birthLocation, liveLocation, livePlace, registerTime, signature, authority, icon) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'icon/pamsdefaulticon.gif')", new Object[]{username, password, realName, idCard, telephone, email, qq, birthDate, birthLocation, liveLocation, livePlace, registerTime, signature, authority})) {
		out.print("success");
	} else {
		out.print("error");
	}
%>
