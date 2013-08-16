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
<script src="js/register.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery-ui-slide.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-timepicker-addon.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />

<link type="text/css" href="css/common.css" rel="stylesheet" />
<link type="text/css" href="css/register.css" rel="stylesheet" />
</head>
<body>
<div id="header"></div>
<div id="main">
  <p class="main_p1"></p>
  <p class="main_p2"></p>
  <p class="main_p3"></p>
  <p class="main_p4"></p>
  <p class="main_title">新 用 户 注 册</p>
  <p class="main_p5"></p>
  <p class="main_p6"></p>
  <p class="main_p7"></p>
  <p class="main_p8"></p>
  <p class="main_p9"></p>
  <p class="main_p10"></p>
  <div class="main_content">
    <table border="0" cellpadding="0" cellspacing="0">
      <tbody>
        <tr>
          <td width="160px"><label for="username">用户名：</label></td>
          <td width="550px"><input name="username" id="username" type="text" class="input_1" maxLength="20" errorMsg="用户名长度：6~20" />
            <i>*</i><span>用户名长度：6~20</span><br />
         </td>
        </tr>
        <tr>
          <td><label for="password">密码：</label></td>
          <td><input name="password" id="password" type="password" class="input_1" maxLength="20" errorMsg="密码长度：6~20" />
            <i>*</i><span>密码长度：6~20</span></td>
        </tr>
        <tr>
          <td><label for="password2">确认密码：</label></td>
          <td><input name="password2" id="password2" type="password" class="input_1" maxLength="20" nullable="false" errorMsg="两次输入的密码不相同" />
            <i>*</i><span></span></td>
        </tr>
        <tr>
          <td><label for="realName">真实姓名：</label></td>
          <td><input name="realName" id="realName" type="text" class="input_1" maxLength="20" />
            <span></span></td>
        </tr>
        <tr>
          <td>证件号码：</td>
          <td><input type="text" id="idCard" name="idCard" class="input_1" maxLength="20" />
            <span></span></td>
        </tr>
        <tr>
          <td>现居住地：</td>
          <td><select class="input_2" id="liveLocation1" name="liveLocation1">
            </select>
            <select class="input_2" id="liveLocation2" name="liveLocation2" style="display:none;">
            </select>
            <select class="input_2" id="liveLocation3" name="liveLocation3" style="display:none;">
            </select>
            <br />
            <input type="text" id="livePlace" name="livePlace" class="input_3" maxLength="20" />
            <span></span></td>
        </tr>
        <tr>
          <td>出生地：</td>
          <td><select class="input_2" id="birthLocation1" name="birthLocation1">
            </select>
            <select class="input_2" id="birthLocation2" name="birthLocation2" style="display:none;">
            </select>
            <select class="input_2" id="birthLocation3" name="birthLocation3" style="display:none;">
            </select>
           </td>
        </tr>
        <tr>
          <td>出生日期：</td>
          <td><input type="text" class="input_1" id="birthDate" name="birthDate" readonly />
          <script type="text/javascript">
          	$("#birthDate").datepicker();
          </script>
          </td>
        </tr>
        <tr>
          <td>电话：</td>
          <td><input type="text" id="telephone" name="telephone" class="input_1" minLength="6" maxLength="20" nullable="true" />
            <span></span></td>
        </tr>
        <tr>
          <td>QQ：</td>
          <td><input type="text" id="qq" name="qq" class="input_1" minLength="6" maxLength="20" nullable="true" />
            <span></span></td>
        </tr>
        <tr>
          <td>E-mail：</td>
          <td><input type="text" id="email" name="email" class="input_1" minLength="6" maxLength="20" nullable="true" />
            <span></span></td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td align="left" colspan="2" style="padding:30px 80px;"><input type="checkbox" id="agreement" name="agreement" />
            &nbsp;&nbsp;我同意<a href="/contact/index.jsp?menu1=0&menu2=2" target="_blank">服务协议</a> 
            <b id="registerBtn">注册</b></td>
        </tr>
      </tfoot>
    </table>
  </div>
</div>
<div id="footer"></div>
</body>
</html>
