<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="main">
    <div class="about" style="border-left:none;"> <span>关于我们</span> <br />
      <ul>
        <li><a href="<%=path %>/contact/index.jsp?menu1=2&menu2=0">关于我们</a></li>
        <li><a href="<%=path %>/contact/index.jsp?menu1=2&menu2=1">联系我们</a></li>
      </ul>
    </div>
    <div class="help"> <span>用户中心</span> <br />
      <ul>
        <li><a href="<%=path %>/contact/index.jsp?menu1=0&menu2=0">帮助中心</a></li>
        <li><a href="<%=path %>/contact/index.jsp?menu1=0&menu2=1">常见问题</a></li>
        <li><a href="<%=path %>/contact/index.jsp?menu1=0&menu2=2">服务协议1</a></li>
      </ul>
    </div>
    <div class="service"> <span>服务支持</span> <br />
      <ul>
        <li>QQ：<a href="#">357966261</a></li>
        <li>E-mail：<a href="mailto:lsw1991abc@163.com">lsw1991abc@163.com</a></li>
      </ul>
    </div>
    <div class="copyright"> <span>版权所有</span> <br />
      <ul>
        <li>&copy;&nbsp;All Rights Reserved 2012</li>
        <li>个人事务管理系统</li>
        <li>By <a href="http://www.lssrc.com" target="_blank">lssrc.com</a> 李世伟</li>
        <li>Version 1.0.0</li>
      </ul>
    </div>
  </div>