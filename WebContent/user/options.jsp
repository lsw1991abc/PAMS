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
<script src="js/jquery.uploadify.js" type="text/javascript" charset="utf-8"></script>
<script src="js/options.js" type="text/javascript" charset="utf-8"></script>
<script src="js/shade.js" type="text/javascript" charset="utf-8"></script>
<link href="css/uploadify.css" type="text/css" rel="stylesheet"/>
<link type="text/css" href="../css/common.css" rel="stylesheet" />
<link type="text/css" href="css/itemInfo.css" rel="stylesheet" />
<link type="text/css" href="css/options.css" rel="stylesheet" />
<script type="text/javascript">
function showAssetsInfo(id) {
	setShadePosition();
	$("#shade_content p span.shade_content_title").html("加载中...");
	$.getJSON("query_assets_id", {id:id}, function(data) {
		var msgArray = eval(data.msg);
		if(msgArray[0].result == "success") {
			
			$("#shade_content p span.shade_content_title").html(msgArray[1].type);
			$("<p><label>账号： </label><span class='text'>" + msgArray[1].account + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>余额： </label><span class='text'>" + msgArray[1].balance + "</span></p>").appendTo("#shade_content div");
		} else {
			$("#shade_content p span.shade_content_title").html("获取失败");
		}
	});
}
</script>
</head>
<body>
<div id="header"></div>
<div id="main">
  <div class="main_head">
    <ul>
      <li class="focus">基本资料</li>
      <li>个性设置</li>
    </ul>
  </div>
  <div class="main_body">
    <div class="main_left">
      <ul>
        <li class="selected" name="li_personal_info">个人信息</li>
        <li name="li_personal_icon">头像修改</li>
        <li style="display:none;" name="li_friend_site">账号绑定</li>
      </ul>
      <ul>
        <li name="li_assets">资产账号设置</li>
        <li style="display:none;" name="li_memorandum_set">备忘提醒设置</li>
      </ul>
    </div>
    <div class="main_content">
      <ul id="personInfo">
        <li><label>真实姓名：</label><span>
          <input name="userRealName" id="userRealName" type="text" value="加载中..." />
          </span></li>
        <li><label>证件号码：</label><span>
          <input type="text" id="IDCard" name="IDCard" value="加载中..."  />
          </span></li>
        <li><label>现居住地：</label><span>
          <input type="text" id="liveLocation" name="liveLocation"  value="加载中..." />
          </span>
        </li>        
        <li><label>出生地：</label><span>
          <input type="text" id="birthLocation" name="birthLocation"  value="加载中..." />
          </span>
        </li>
        
        <li><label>出生日期：</label><span>
          <input type="text" id="birthDate" name="birthDate"  value="加载中..." />
          </span>
        </li>
        <li><label>电话：</label><span>
          <input type="text" id="tel" name="tel" value="加载中..." />
          </span></li>
        <li><label>QQ：</label><span>
          <input type="text" id="qq" name="qq" value="加载中..." />
          </span></li>
        <li><label>E-mail：</label><span>
          <input type="text" id="email" name="email" value="加载中..." />
          </span></li>
          <li><label>密码：</label><span>
          <input type="password" id="password" name="password" />
          </span>&nbsp;&nbsp;&nbsp;<i style="font-size:13px;">密码留空为不修改</i></li>
          <li><label>确认密码：</label><span>
          <input type="password" id="password2" name="password2" />
          </span></li>
        <li style="border-bottom:none; height:50px; line-height:50px;"><input type="button" id="personInfoSave" value="保存" style="width:100px; height:35px; color:#FFF; line-height:35px; margin-left:150px; cursor:pointer; background:#666666;" /></li>
      </ul>
      <script type="text/javascript">
      		var realName;
      		var idCard;
      		var liveLocation;
      		var birthLocation;
      		var birthDate;
      		var telephone;
      		var qq;
      		var email;
      		var password;
      		var password2;
        	$.getJSON("queryMyInfo", function(data) {
        		var dataArray = eval(data.msg);
        		if(dataArray[0].result == "success") {
        			$("#personInfo #userRealName").val(realName = dataArray[1].realName);
        			$("#personInfo #IDCard").val(idCard = dataArray[1].idCard);
        			$("#personInfo #liveLocation").val(liveLocation = dataArray[1].liveLocation);
        			$("#personInfo #birthLocation").val(birthLocation = dataArray[1].birthLocation);
        			$("#personInfo #birthDate").val(birthDate = dataArray[1].birthday);
        			$("#personInfo #tel").val(telephone = dataArray[1].telephone);
        			$("#personInfo #qq").val(qq = dataArray[1].qq);
        			$("#personInfo #email").val(email = dataArray[1].email);
        			$("#personInfo #personInfoSave").click(function() {
        				password = $.trim($("#personInfo #password").val());
        				password2 = $.trim($("#personInfo #password2").val());
        				if(password == password2) {
        					realName = $.trim($("#personInfo #userRealName").val());
            				idCard = $.trim($("#personInfo #IDCard").val());
            				liveLocation = $.trim($("#personInfo #liveLocation").val());
            				birthLocation = $.trim($("#personInfo #birthLocation").val());
            				birthDate = $.trim($("#personInfo #birthDate").val());
            				telephone = $.trim($("#personInfo #tel").val());
            				qq = $.trim($("#personInfo #qq").val());
            				email = $.trim($("#personInfo #email").val());
                			$("#personInfo #personInfoSave").val("修改中...");
            				$.post("update_user", {password:password, realName:realName, idCard:idCard, telephone:telephone, email:email, qq:qq, birthDate:birthDate, birthLocation:birthLocation, liveLocation:liveLocation}, function(data) {
            					dataArray = eval(data.msg);
            					if(dataArray[0].result == "success") {
            						alert("修改成功");
            						$("#personInfo #personInfoSave").val("保存");
            						$("#personInfo #password").val("");
            						$("#personInfo #password2").val("");
            						$("#personInfo #userRealName").val(realName);
            	        			$("#personInfo #IDCard").val(idCard);
            	        			$("#personInfo #liveLocation").val(liveLocation);
            	        			$("#personInfo #birthLocation").val(birthLocation);
            	        			$("#personInfo #birthDate").val(birthDate);
            	        			$("#personInfo #tel").val(telephone);
            	        			$("#personInfo #qq").val(qq);
            	        			$("#personInfo #email").val(email);
            					} else {
            						alert("修改失败");
            					}
            				});
        				} else {
        					alert("确认密码错误");
        				}
        			});
        		} else {
        			alert("获取失败");
        		}
        	});
        </script>
      <ul id="personIcon">
        <li style="height:260px; border-bottom:none;">
        	<img src="#" id="userIcon_img" width="240px" height="240px" style="margin-left:170px;" />
        </li>
        <li style="height:auto; border-bottom:none;">        
          <input type="file" name="uploadify" id="uploadify" />
          <input type="button" value="上传" id="uploadIcon" style=" float:left;width:100px; color:#FFF; margin-left:50px; cursor:pointer; background:#666666;" />
       </li>
       </ul>
       <script type="text/javascript">
	       $.getJSON("index", function(data) {
		   		var msgArray = eval("("+data.msg+")");		
		   		$("#personIcon #userIcon_img").attr("src", msgArray[0].icon);
		   	});
	       $("#uploadify").each(function(i,obj) {  
		    	$(obj).uploadify({	      
		     	   'swf':'js/uploadify.swf',
					'uploader':'updateIcon.action',
					'auto':false,
					'buttonText':'浏览...',
			        'debug':false,
			        'fileObjName':'uploadify',//文件对象的名称中使用您的服务器端脚本
			        'fileSizeLimit':'2048KB',//限制文件大小
					'fileTypeDesc' : 'Image Files',//'fileTypeDesc' : 'Any old file you want...',
					'fileTypeExts' : '*.gif; *.jpg; *.png',//必须有上条,才能有这条
			        'method':'post',
					'multi':false,//设置为false,以允许只有一个文件选择在一个时间
					'preventCaching':true,//如果设置为true,一个随机值添加到SWF文件的URL,这样它就不会缓存。这将冲突与任何现有的查询字符串参数对SWF文件的URL
					'removeCompleted':false,//是否自动删除完成的项目
					'uploadLimit':1,//上传数量限制
			     //   'width':300,//按钮宽度
			     //   'height':20
			   	 });
			});
	       $("#personIcon #uploadIcon").click(function() {
	    	   $("#uploadify").uploadify("upload","*");
	       });
       </script>
      <ul>
      	<li>账号绑定</li>
      </ul>
      <ul id="my_assets_list">
        <li><span class="assets_type">账号</span><span class="assets_add" id="add_assets_btn">添加</span></li>
        <li><span class="item_title">农业银行</span><span class="item_account">6228888888888888888</span><span class="item_edit">查看</span></li>
      </ul>
      <script type="text/javascript">
      	$("#my_assets_list li:gt(0)").remove();
      	$.getJSON("query_assets_user", function(data) {
      		var msgArray = eval("("+data.msg+")");
      		var resultArray = msgArray.result;
      		if(resultArray[0].resultMsg == "success") {
      			var dataArray = msgArray.data;
      			$(dataArray).each(function(index, item) {
      				$("<li><span class='item_title'>"+item.type+"</span><span class='item_account'>"+item.account+"</span><a href=\"javascript:showAssetsInfo('"+item.id+"');\">查看</a></li>").appendTo("#my_assets_list");
      			});
      		} else {
      			alert("账号获取失败");
      		}
      	});
      </script>
      <ul>
      	<li>备忘提醒设置</li>
      </ul>
    </div>
    <br class="spacer" />
  </div>
</div>
<div id="footer"></div>
</body>
</html>
