<%@page import="com.lssrc.pams.util.UtilFactory"%>
<%@page import="com.lssrc.pams.util.PamsConstant"%>
<%@page import="com.lssrc.pams.util.DBUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	String parent = request.getParameter("parent")==null?"0":request.getParameter("parent").toString();
	List locations = DBUtils.queryForList("select id,name,parentId from pams_location where parentId=? order by name asc", new Object[]{Integer.parseInt(parent)});
	if(locations != null) {
		Map map = null;
		String stringList = "<option value=''>--请选择--</option>";
		for(int i = 0; i < locations.size(); i++) {
			map = (Map) locations.get(i);
			stringList += "<option value='"+map.get("id")+"' parentId='"+map.get("parentId")+"'>"+map.get("name")+"</option>";
		}
		out.print(stringList);
	} else {
		out.print("<script type='text/javascript'>alert('数据获取错误');</script>");
	}
%>
