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
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<link type="text/css" href="css/common.css" rel="stylesheet" />
<link type="text/css" href="css/index.css" rel="stylesheet" />
</head>
<body>
	<div id="header"></div>
	<div id="main">
		<div class="main">
			<h2>用 户 登 录</h2>
			<div class="loginBg"></div>
			<div class="login" style="padding-top:5px;">
				<form action="j_spring_security_check" method="post" name="f" id="loginForm">
				<table style="width:260px;" align="center">
					<tr>
						<td style="width:130px;">用&nbsp;户&nbsp;名</td>
						<td style="width:130px; text-align:right;"><a
							href="register.jsp">新用户注册</a>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="text"
							style="height:25px; width:240px;" id="j_username" name="j_username" value="lsw1991abc" maxlength="20" />
						</td>
					</tr>
					<tr>
						<td>密&nbsp;码</td>
						<td style="text-align:right;"><a href="#" style="display:none;">忘记密码？</a>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="password"
							style="height:25px; width:240px;" id="j_password" name="j_password" value="lsw1991abc" maxlength="20" />
						</td>
					</tr>
					<tr style="display:none;">
						<td>验&nbsp;证&nbsp;码</td>
						<td align="center">&nbsp;</td>
					</tr>
					<tr style="display:none;">
						<td align="center"><input type="text" maxlength="4" style="height:25px; width:100px; font-size:16px;" id="validateString" name="validateString" />
						</td>
						<td style=" text-align:center;" style="padding-top:2px;"><img src="validateImageAction" height="28px" width="100px" id="validataImg" name="validataImg"  title="点击刷新验证码" style="cursor:pointer;" />
						</td>
					</tr>
					<tr>
						<td colspan="2" style="height:30px;"></td>
					</tr>
					<tr style="text-align:center">
						<td colspan="2" align="center"><span class="loginbtn">登&nbsp;&nbsp;陆</span>
						</td>
					</tr>
					<tr style="display:none;">
						<td colspan="2">合作登陆</td>
					</tr>
				</table>
				</form>
			</div>
			<div class="selector">
				<span style="background-position:bottom"></span> <span></span> <span></span>
			</div>
		</div>
		<div id="indexBg">
			<div class="indexBg_c"
				style="background-image:url(image/index_bg1.png);"></div>
			<div class="indexBg_c"
				style="background-image:url(image/index_bg2.png);"></div>
			<div class="indexBg_c"
				style="background-image:url(image/index_bg3.png);"></div>
		</div>
	</div>
	<div id="download" style="display:none"></div>
	<div id="footer"></div>
</body>
</html>
