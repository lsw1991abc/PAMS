$(function() {
	$("#header").load("header.jsp");
	$("#footer").load("footer.jsp");
	var username;
	var usernameBoolean=false;
	var successImg="<img src='image/check.png' />";
	$("#username").focus(function() {
		$("#username").parent().removeClass("error").css("color","#000");
	}).blur(function() {
		username=$.trim($("#username").val());
		if(username.length<6) {
			$("#username").parent().addClass("error");
			$("#username").next().next().html($("#username").attr("errorMsg")).css("color","red");
			usernameBoolean=false;
			changeRegisterBtn();
		} else {
			$("#username").next().next().html("检查中....").css("color","green");
			$.get("validateUser.jsp", {username:$("#username").val()}, function(data) {
				if($.trim(data) == "0") {
					$("#username").next().next().html($("#username").val() + " 可用").css("color","green");
					usernameBoolean=true;
					changeRegisterBtn();
				} else {
					$("#username").next().next().html("用户名已存在").css("color","red");
				}
			});
		}
	});
	
	var password;
	$("#password").focus(function() {
		$("#password").parent().removeClass("error");
	}).blur(function() {
		password=$.trim($("#password").val());
		if(password.length<6) {
			$("#password").parent().addClass("error");
			$("#password").next().next().html($("#password").attr("errorMsg"));
		} else {
			$("#password").next().next().html(successImg);
		}
	});
	var password2;
	var passwordBoolean=false;
	$("#password2").focus(function() {
		$("#password2").parent().removeClass("error");
	}).blur(function() {
		password2=$.trim($("#password2").val());
		if(password2==""||password2==null) {
			$("#password2").parent().addClass("error");
			$("#password2").next().next().html("确认密码不能为空");
			passwordBoolean=false;
			changeRegisterBtn();
		} else if(password!=password2) {
			$("#password2").parent().addClass("error");
			$("#password2").next().next().html($("#password2").attr("errorMsg"));
			passwordBoolean=false;
			changeRegisterBtn();
		} else {
			$("#password2").next().next().html(successImg);
			passwordBoolean=true;
			changeRegisterBtn();
		}
	});
	
	var agreementBoolean=false;
	$("#agreement").click(function() {
		if($(this).attr("checked")) {
			agreementBoolean=true;
			changeRegisterBtn();
		} else {
			agreementBoolean=false;
			changeRegisterBtn();
		}
	});
	
	function changeRegisterBtn() {
		if(usernameBoolean&&passwordBoolean&&agreementBoolean) {
			$("#registerBtn").addClass("clickable");
		} else {
			$("#registerBtn").removeClass("clickable");
		}
	}
	
	$("#registerBtn").click(function() {
		var b = $(this);
		if($(b).hasClass("clickable")) {
			$(b).html("注册中...").removeClass("clickable");
			var username = $.trim($("#username").val());
			var password = $.trim($("#password").val());
			var realName = $.trim($("#realName").val());
			var idCard = $.trim($("#idCard").val());
			var telephone = $.trim($("#telephone").val());
			var email = $.trim($("#email").val());
			var qq = $.trim($("#qq").val());
			var birthDate = $.trim($("#birthDate").val());
			var birthLocation = $.trim($("#birthLocation1").find("option:selected").text())+$.trim($("#birthLocation2").find("option:selected").text())+$.trim($("#birthLocation3").find("option:selected").text());
			var liveLocation = $.trim($("#liveLocation1").find("option:selected").text())+$.trim($("#liveLocation2").find("option:selected").text())+$.trim($("#liveLocation3").find("option:selected").text());
			var livePlace = $.trim($("#livePlace").val());
			
			$.post("reg.jsp",{username:username, password:password, realName:realName, idCard:idCard, telephone:telephone, email:email, qq:qq, birthDate:birthDate, birthLocation:birthLocation, liveLocation:liveLocation, livePlace:livePlace},function(data) {
				if($.trim(data) == "success") {
					$(b).html("注册").addClass("clickable");
					alert("注册成功");
					location.href="index.jsp";
				} else {
					alert("注册失败！请联系管理员");
					$(b).html("注册").addClass("clickable");
				}
			});
		}
	});
	
	$.get("queryByParent.jsp", {parent:1}, function(data) {
		$("#liveLocation1").html(data);
		$("#birthLocation1").html(data);
	});
	
	var liveLocation1;
	var liveLocation2;
	var birthLocation1;
	var birthLocation2;
	$("#liveLocation1").change(function() {
		liveLocation1 = $.trim($("#liveLocation1").val());
		if(liveLocation1 != "") {
			$.get("queryByParent.jsp", {parent:liveLocation1}, function(data) {
				$("#liveLocation2").html(data).css("display","");
				$("#liveLocation3").css("display","none");
			});
		} else {
			$("#liveLocation2").css("display","none");
			$("#liveLocation3").css("display","none");
		}
	});
	$("#liveLocation2").change(function() {
		liveLocation2 = $.trim($("#liveLocation2").val());
		if(liveLocation2 != "") {
			$.get("queryByParent.jsp", {parent:liveLocation2}, function(data) {
				$("#liveLocation3").html(data).css("display","");
			});
		} else {
			$("#liveLocation3").css("display","none");
		}
	});
	
	$("#birthLocation1").change(function() {
		birthLocation1 = $.trim($("#birthLocation1").val());
		if(birthLocation1 != "") {
			$.get("queryByParent.jsp", {parent:birthLocation1}, function(data) {
				$("#birthLocation2").html(data).css("display","");
				$("#birthLocation3").css("display","none");
			});
		} else {
			$("#birthLocation2").css("display","none");
			$("#birthLocation3").css("display","none");
		}
	});
	$("#birthLocation2").change(function() {
		birthLocation2 = $.trim($("#birthLocation2").val());
		if(birthLocation2 != "") {
			$.get("queryByParent.jsp", {parent:birthLocation2}, function(data) {
				$("#birthLocation3").html(data).css("display","");
			});
		} else {
			$("#birthLocation3").css("display","none");
		}
	});

	
});



