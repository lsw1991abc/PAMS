<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="js/userRight.js" type="text/javascript" charset="utf-8"></script>

<script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery-ui-slide.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-timepicker-addon.js"></script>
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css" />

<title>userRight.jsp</title>
<style type="text/css">
.quickPanel .module_content span{
	cursor:pointer;
}
#signature{
	display:block;
	min-height:15px;
}
</style>
</head>
<body>
<div class="userIcon">
	<div class="userIcon_pic">
        <div class="userIcon_update">&nbsp;</div>
		<p title="账户设置:修改个人资料,修改头像...">账户设置</p>
		<span>&nbsp;</span> <img src="#" id="userIcon_img" /> 
	</div>
</div>
<script type="text/ecmascript">
$(".userIcon_pic").hover(function() {
		$(".userIcon_update").dequeue();
		$(".userIcon_update").next().dequeue();
		$(".userIcon_update").animate({
			top:"130px"
		}, 400);
		$(".userIcon_update").next().animate({
			top:"130px"
		}, 400);
	}, function() {
		$(".userIcon_update").dequeue();
		$(".userIcon_update").next().dequeue();
		$(".userIcon_update").animate({
			top:"160px"
		}, 400);
		$(".userIcon_update").next().animate({
			top:"160px"
		}, 400);
	});
	
	$(".userIcon_update").next().hover(function() {		
		$(this).addClass("focus");
	}, function() {
		$(this).removeClass("focus");
	}).click(function() {
		window.location.href="options.jsp";
	});
</script>
<div class="userInfo">
	<ul>
		<li>个性签名：
			<p id="signature"></p>
			<textarea rows="3" cols="22" style="resize: none; display:none; font-size:13px;" id="signatureTextarea"></textarea>
		</li>
	</ul>
</div>
<script type="text/ecmascript">
	$.getJSON("index", function(data) {
		var msgArray = eval("("+data.msg+")");		
		$(".userInfo #signature").html(msgArray[0].signature);
		$(".userIcon #userIcon_img").attr("src", msgArray[0].icon);
	});
	var signature;
	$("#signature").click(function() {
		signature=$.trim($(this).html());
		$(this).css("display","none");
		$("#signatureTextarea").css("display","").val(signature).focus();
	});
	
	$("#signatureTextarea").keypress(function(event) {
		if(event.keyCode==13) {
			if($.trim($("#signatureTextarea").val()).length<=140) {
				if($.trim($("#signatureTextarea").val())!=signature) {
					signature=$.trim($("#signatureTextarea").val());
					$.post("updateSignature", {signature:signature}, function(data) {
						console.log(data.msg);
					});
				}
				$(this).css("display","none");
				$("#signature").html(signature).css("display","");
			} else {
				alert("长度不能大于140");
			}
		} else if(event.keyCode==27) {
			$("#signatureTextarea").css("display","none").val("");
			$("#signature").css("display","");
		}
	});
</script>

<div class="quickPanel">
	<div class="module_title">
   		<h2>快捷面板</h2>
	</div>
	<div class="module_content">
		<span action="memorandum">新备忘录</span><span action="task">新任务</span>
        <br /><span action="dairy">新生活记事</span><span action="easyNote">新简易记事</span>
        <br /><span action="transaction">新交易记录</span>
	</div>
</div>

<div class="advice">
	<div class="module_title">
		<h2>提交建议</h2>
	</div>
	<div class="module_content">
		您对我们的网站有什么建议？<br />赶紧告诉我们吧！！
        <textarea rows="5" cols="23" style="resize: none; font-size:13px;" id="adviceTextarea"></textarea>
        <input class="advice_btn" type="button" value="提交" />
        <p style="color:#060"></p>
	</div>
</div>
<script type="text/ecmascript">
var adviceTextareaValue;
	$(".advice_btn").click(function() {
		console.log("you click");
		adviceTextareaValue=$.trim($("#adviceTextarea").val());
		if(adviceTextareaValue!=""&&adviceTextareaValue!=null&&adviceTextareaValue.length<=200) {
			$(".advice_btn").hide().next().html("正在提交...");
			$.post("add_advice", {content:adviceTextareaValue}, function(data) {
				if(eval(data.msg)[0].result=="success") {
					$(".advice_btn").next().html("谢谢您的建议。O(∩_∩)O~");
					$("#adviceTextarea").val("");
				} else {
					$(".advice_btn").next().html("提交失败。~~o(>_<)o ~~");
				}
				$(".advice_btn").show();
			});
		} else {
			$(".advice_btn").next().html("写点吧，不要吝啬~~最大长度200");
		}		
	});
</script>
</body>
</html>



